package com.heaven.video.manager;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.util.Util;

import java.util.UUID;

/**
 * FileName: com.heaven.video.manager.VideoInfo.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-10-04 11:55
 *
 * @version V1.0 数字多媒体信息
 */
public class VideoInfo {
    @NonNull
    public String[] uris;//媒体uri
    public String[] extensions;//媒体类型

    public String adTagUriString;//广告


    public UUID drmSchemeUuid;//数字版权标示
    public String drmLicenseUrl;//数字版权url
    public String[] drmKeyRequestPropertiesArray;//数字版权key

    @DefaultRenderersFactory.ExtensionRendererMode
    public int extensionRendererMode = DefaultRenderersFactory.EXTENSION_RENDERER_MODE_ON;//DefaultRenderersFactory.EXTENSION_RENDERER_MODE_PREFER

    public SimpleExoPlayerView simpleExoPlayerView;

    public UUID getDrmUuid(String typeString) throws ParserException {
        switch (Util.toLowerInvariant(typeString)) {
            case "widevine":
                return C.WIDEVINE_UUID;
            case "playready":
                return C.PLAYREADY_UUID;
            case "cenc":
                return C.CLEARKEY_UUID;
            default:
                try {
                    return UUID.fromString(typeString);
                } catch (RuntimeException e) {
                    throw new ParserException("Unsupported drm wx_type: " + typeString);
                }
        }
    }

}
