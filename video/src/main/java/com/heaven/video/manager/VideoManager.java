package com.heaven.video.manager;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm;
import com.google.android.exoplayer2.drm.HttpMediaDrmCallback;
import com.google.android.exoplayer2.drm.UnsupportedDrmException;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ConcatenatingMediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.DebugTextViewHelper;
import com.google.android.exoplayer2.ui.PlaybackControlView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;
import com.heaven.video.EventLogger;
import com.heaven.video.R;
import com.heaven.video.TrackSelectionHelper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * FileName: com.heaven.video.manager.VideoManager.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-10-03 17:14
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class VideoManager implements View.OnClickListener, Player.EventListener,
        PlaybackControlView.VisibilityListener {

    private SimpleExoPlayerView simpleExoPlayerView;

    private DataSource.Factory mediaDataSourceFactory;
    private SimpleExoPlayer player;
    private DefaultTrackSelector trackSelector;
    private TrackSelectionHelper trackSelectionHelper;
    private TrackGroupArray lastSeenTrackGroupArray;
    private Handler mainHandler;
    private EventLogger eventLogger;

    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();

    private boolean shouldAutoPlay;
    private int resumeWindow;
    private long resumePosition;

    // Fields used only for ad playback. The ads loader is loaded via reflection.
    private Object imaAdsLoader; // com.google.android.exoplayer2.ext.ima.ImaAdsLoader
    private Uri loadedAdTagUri;
    private ViewGroup adOverlayViewGroup;

    private boolean inErrorState;

    private static VideoManager instance;

    private VideoManager(Context context) {
        mainHandler = new Handler();
        mediaDataSourceFactory = buildDataSourceFactory(context, true);
    }

    public static VideoManager getInstance(Context context) {
        if (instance == null) {
            instance = new VideoManager(context);
        }
        return instance;
    }

    private void releasePlayer() {
        if (player != null) {
            shouldAutoPlay = player.getPlayWhenReady();
            updateResumePosition();
            player.release();
            player = null;
            trackSelector = null;
            trackSelectionHelper = null;
            eventLogger = null;
        }
    }

    private void updateResumePosition() {
        resumeWindow = player.getCurrentWindowIndex();
        resumePosition = Math.max(0, player.getContentPosition());
    }


    public SimpleExoPlayerView startPlay(Context context, VideoInfo videoInfo) {
        resetPlay();
        boolean needNewPlayer = player == null;
        if (needNewPlayer) {

            simpleExoPlayerView = videoInfo.simpleExoPlayerView == null? new SimpleExoPlayerView(context) : videoInfo.simpleExoPlayerView;

            //创建轨道选择器工厂
            TrackSelection.Factory adaptiveTrackSelectionFactory =
                    new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);
            //创建轨道选择器(音频 视频 字幕)
            trackSelector = new DefaultTrackSelector(adaptiveTrackSelectionFactory);
            //轨道选择器帮助类
            trackSelectionHelper = new TrackSelectionHelper(trackSelector, adaptiveTrackSelectionFactory);
            lastSeenTrackGroupArray = null;
            eventLogger = new EventLogger(trackSelector);

            //数字媒体版权管理
            DrmSessionManager<FrameworkMediaCrypto> drmSessionManager = null;
            if (videoInfo.drmSchemeUuid != null) {
                int errorStringId = R.string.error_drm_unknown;
                try {
                    drmSessionManager = buildDrmSessionManagerV18(context, videoInfo.drmSchemeUuid, videoInfo.drmLicenseUrl,
                            videoInfo.drmKeyRequestPropertiesArray);
                } catch (UnsupportedDrmException e) {
                    errorStringId = e.reason == UnsupportedDrmException.REASON_UNSUPPORTED_SCHEME
                            ? R.string.error_drm_unsupported_scheme : R.string.error_drm_unknown;
                }
                if (drmSessionManager == null) {

                    Toast.makeText(context, errorStringId, Toast.LENGTH_LONG).show();
                    return simpleExoPlayerView;
                }
            }

            //媒体数据渲染工厂
            DefaultRenderersFactory renderersFactory = new DefaultRenderersFactory(context,
                    drmSessionManager, videoInfo.extensionRendererMode);

            //媒体播放控制
            player = ExoPlayerFactory.newSimpleInstance(renderersFactory, trackSelector);
            player.addListener(this);
            player.addListener(eventLogger);
            player.setAudioDebugListener(eventLogger);
            player.setVideoDebugListener(eventLogger);
            player.setMetadataOutput(eventLogger);

            simpleExoPlayerView.setPlayer(player);
            player.setPlayWhenReady(shouldAutoPlay);
        }

        if (videoInfo.uris != null && videoInfo.uris.length > 0) {
            String[] uriStrings = videoInfo.uris;
            Uri[] uris = new Uri[uriStrings.length];
            for (int i = 0; i < uriStrings.length; i++) {
                uris[i] = Uri.parse(uriStrings[i]);
            }
            String[] extensions = videoInfo.extensions;
            if (extensions == null) {
                extensions = new String[uriStrings.length];
            }


            //创建媒体数据源
            MediaSource[] mediaSources = new MediaSource[videoInfo.uris.length];
            for (int i = 0; i < videoInfo.uris.length; i++) {
                mediaSources[i] = buildMediaSource(context, uris[i], extensions[i]);
            }
            MediaSource mediaSource = mediaSources.length == 1 ? mediaSources[0]
                    : new ConcatenatingMediaSource(mediaSources);

            //广告
            if (videoInfo.adTagUriString != null) {
                Uri adTagUri = Uri.parse(videoInfo.adTagUriString);
                if (!adTagUri.equals(loadedAdTagUri)) {
                    releaseAdsLoader();
                    loadedAdTagUri = adTagUri;
                }
                try {
                    mediaSource = createAdsMediaSource(context, mediaSource, Uri.parse(videoInfo.adTagUriString));
                } catch (Exception e) {
                    Toast.makeText(context, R.string.ima_not_loaded, Toast.LENGTH_LONG).show();
                }
            } else {
                releaseAdsLoader();
            }
            boolean haveResumePosition = resumeWindow != C.INDEX_UNSET;
            if (haveResumePosition) {
                player.seekTo(resumeWindow, resumePosition);
            }
            player.prepare(mediaSource, !haveResumePosition, false);
            inErrorState = false;
        }
        return simpleExoPlayerView;
    }

    public void resumePlay() {

    }

    /*创建媒体数据源*/
    private MediaSource buildMediaSource(Context context, Uri uri, String overrideExtension) {
        int type = TextUtils.isEmpty(overrideExtension) ? Util.inferContentType(uri)
                : Util.inferContentType("." + overrideExtension);
        switch (type) {
            case C.TYPE_SS:
                return new SsMediaSource(uri, buildDataSourceFactory(context, false),
                        new DefaultSsChunkSource.Factory(mediaDataSourceFactory), mainHandler, eventLogger);
            case C.TYPE_DASH:
                return new DashMediaSource(uri, buildDataSourceFactory(context, false),
                        new DefaultDashChunkSource.Factory(mediaDataSourceFactory), mainHandler, eventLogger);
            case C.TYPE_HLS:
                return new HlsMediaSource(uri, mediaDataSourceFactory, mainHandler, eventLogger);
            case C.TYPE_OTHER:
                return new ExtractorMediaSource(uri, mediaDataSourceFactory, new DefaultExtractorsFactory(),
                        mainHandler, eventLogger);
            default: {
                throw new IllegalStateException("Unsupported wx_type: " + type);
            }
        }
    }


    /* 创建数字版权协议管理*/
    private DrmSessionManager<FrameworkMediaCrypto> buildDrmSessionManagerV18(Context context, UUID uuid,
                                                                              String licenseUrl, String[] keyRequestPropertiesArray) throws UnsupportedDrmException {
        HttpMediaDrmCallback drmCallback = new HttpMediaDrmCallback(licenseUrl,
                buildHttpDataSourceFactory(context, false));
        if (keyRequestPropertiesArray != null) {
            for (int i = 0; i < keyRequestPropertiesArray.length - 1; i += 2) {
                drmCallback.setKeyRequestProperty(keyRequestPropertiesArray[i],
                        keyRequestPropertiesArray[i + 1]);
            }
        }
        return new DefaultDrmSessionManager<>(uuid, FrameworkMediaDrm.newInstance(uuid), drmCallback,
                null, mainHandler, eventLogger);
    }


    /**
     * Returns a new DataSource factory.
     *
     * @param useBandwidthMeter
     *         Whether to set {@link #BANDWIDTH_METER} as a listener to the new
     *         DataSource factory.
     *
     * @return A new DataSource factory.
     */
    private DataSource.Factory buildDataSourceFactory(Context context, boolean useBandwidthMeter) {
        return buildDataSourceFactory(context, useBandwidthMeter ? BANDWIDTH_METER : null);
    }


    public DataSource.Factory buildDataSourceFactory(Context context, DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultDataSourceFactory(context, bandwidthMeter,
                buildHttpDataSourceFactory(context, bandwidthMeter));
    }

    public HttpDataSource.Factory buildHttpDataSourceFactory(Context context, DefaultBandwidthMeter bandwidthMeter) {
        return new DefaultHttpDataSourceFactory(Util.getUserAgent(context, "ExoPlayerDemo"), bandwidthMeter);
    }

    /**
     * Returns a new HttpDataSource factory.
     *
     * @param useBandwidthMeter
     *         Whether to set {@link #BANDWIDTH_METER} as a listener to the new
     *         DataSource factory.
     *
     * @return A new HttpDataSource factory.
     */
    private HttpDataSource.Factory buildHttpDataSourceFactory(Context context, boolean useBandwidthMeter) {
        return new DefaultHttpDataSourceFactory(Util.getUserAgent(context, "ExoPlayerDemo"), BANDWIDTH_METER);
    }

    /**
     * Returns an ads media source, reusing the ads loader if one exists.
     *
     * @throws Exception
     *         Thrown if it was not possible to create an ads media source, for example, due
     *         to a missing dependency.
     */
    private MediaSource createAdsMediaSource(Context context, MediaSource mediaSource, Uri adTagUri) throws Exception {
        // Load the extension source using reflection so the demo app doesn't have to depend on it.
        // The ads loader is reused for multiple playbacks, so that ad playback can resume.
        Class<?> loaderClass = Class.forName("com.google.android.exoplayer2.ext.ima.ImaAdsLoader");
        if (imaAdsLoader == null) {
            imaAdsLoader = loaderClass.getConstructor(Context.class, Uri.class)
                    .newInstance(this, adTagUri);
            adOverlayViewGroup = new FrameLayout(context);
            // The demo app has a non-null overlay frame layout.
            simpleExoPlayerView.getOverlayFrameLayout().addView(adOverlayViewGroup);
        }
        Class<?> sourceClass =
                Class.forName("com.google.android.exoplayer2.ext.ima.ImaAdsMediaSource");
        Constructor<?> constructor = sourceClass.getConstructor(MediaSource.class,
                DataSource.Factory.class, loaderClass, ViewGroup.class);
        return (MediaSource) constructor.newInstance(mediaSource, mediaDataSourceFactory, imaAdsLoader,
                adOverlayViewGroup);
    }

    private void releaseAdsLoader() {
        if (imaAdsLoader != null) {
            try {
                Class<?> loaderClass = Class.forName("com.google.android.exoplayer2.ext.ima.ImaAdsLoader");
                Method releaseMethod = loaderClass.getMethod("release");
                releaseMethod.invoke(imaAdsLoader);
            } catch (Exception e) {
                // Should never happen.
                throw new IllegalStateException(e);
            }
            imaAdsLoader = null;
            loadedAdTagUri = null;
            simpleExoPlayerView.getOverlayFrameLayout().removeAllViews();
        }
    }

    private void resetPlay() {
        releasePlayer();
        clearResumePosition();
    }


    private void clearResumePosition() {
        resumeWindow = C.INDEX_UNSET;
        resumePosition = C.TIME_UNSET;
    }

    public SimpleExoPlayerView getCurrentVideo() {
        return simpleExoPlayerView;
    }

    /*******************触摸反馈区域*********************/
    @Override
    public void onClick(View view) {

    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onVisibilityChange(int visibility) {

    }

    /*******************触摸反馈区域*********************/
}
