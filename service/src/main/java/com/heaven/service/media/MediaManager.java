package com.heaven.service.media;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.heaven.service.aidl.MediaBean;
import com.heaven.service.aidl.MediaFolder;
import com.orhanobut.logger.Logger;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * FileName: com.heaven.service.media.MediaManager.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-04-08 15:10
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class MediaManager {
    public static final int TYPE_IMAGE = 1;
    public static final int TYPE_VIDEO = 2;
    private static final String[] IMAGE_PROJECTION = {
            MediaStore.Images.Media.DATA,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
    private static final String[] VIDEO_PROJECTION = {
            MediaStore.Video.Media.DATA,//视频的绝对路径
            MediaStore.Video.Media.DISPLAY_NAME,//视频在sd卡中的名称
            MediaStore.Video.Media.DATE_ADDED,
            MediaStore.Video.Media.MIME_TYPE,
            MediaStore.Video.Media.SIZE,//视频文件的大小
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DURATION,//视频时长
            MediaStore.Video.Media.ARTIST,//艺术家
            MediaStore.Images.Media.BUCKET_DISPLAY_NAME};

    private Context mContext;
    private LocalMediaLoadListener mLocalMediaLoadListener;
    private static List<MediaFolder> mFolderList = new ArrayList<>();
    private static List<MediaFolder> mVideoFolderList = new ArrayList<>();
    private static List<MediaFolder> mImageFolderList = new ArrayList<>();

    public MediaManager(Context context) {
        this.mContext = context;
        this.mLocalMediaLoadListener = (LocalMediaLoadListener) context;
    }


    public void startScan(boolean needReport) {
        Logger.i("startScan");
        mFolderList.clear();
        mVideoFolderList.clear();
        mImageFolderList.clear();
        initPhotoData(mContext);
        initVideoData(mContext);
        if (needReport) {
            if (mLocalMediaLoadListener != null) {
                mLocalMediaLoadListener.loadComplete(mFolderList);
            }
        }
        Logger.i("startScan--end");
    }

    /**
     * 得到图片数据
     */
    private void initPhotoData(Context context) {
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                IMAGE_PROJECTION,
                IMAGE_PROJECTION[4] + ">0 AND " +
                        IMAGE_PROJECTION[3] + "=? OR " +
                        IMAGE_PROJECTION[3] + "=? OR " +
                        IMAGE_PROJECTION[3] + "=? OR " +
                        IMAGE_PROJECTION[3] + "=? OR " +
                        IMAGE_PROJECTION[3] + "=? ",
                new String[]{"image/jpeg", "image/png", "image/jpg", "image/gif", "image/bmp"},
                IMAGE_PROJECTION[2] + " DESC");

        if (cursor == null || cursor.getCount() == 0) {
            return;
        }
        while (cursor.moveToNext()) {
            String path = cursor.getString(cursor.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
            if (TextUtils.isEmpty(path) || !new File(path).exists()) {
                //过滤不存在的
                continue;
            }
            String name = cursor.getString(cursor.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
            long dateTime = cursor.getLong(cursor.getColumnIndexOrThrow(IMAGE_PROJECTION[2]));
            String ext = cursor.getString(cursor.getColumnIndexOrThrow(IMAGE_PROJECTION[3]));
            long size = cursor.getLong(cursor.getColumnIndexOrThrow(IMAGE_PROJECTION[4]));
            String folder = cursor.getString(cursor.getColumnIndexOrThrow(IMAGE_PROJECTION[6]));

            MediaFolder imageMediaFolder = getFolder(path, mImageFolderList);

            MediaFolder allMediaFolder = getFolder(path, mFolderList);

            File parent = new File(path).getParentFile();
            MediaBean mediaBean = new MediaBean(path, name, dateTime, false, size, 0, parent.getPath(), getExt(name));
            imageMediaFolder.addBean(mediaBean);
            allMediaFolder.addBean(mediaBean);
//            mImageFolderList.add(mediaBean);
        }
        cursor.close();
    }

    /**
     * 得到视频数据
     *
     * @return folder
     */
    private void initVideoData(Context context) {
        Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                VIDEO_PROJECTION,
                VIDEO_PROJECTION[4] + ">0",
                null,
                VIDEO_PROJECTION[2] + " DESC");
        if (cursor == null || cursor.getCount() == 0) {
            return;
        }
        while (cursor.moveToNext()) {
            String path = cursor.getString(cursor.getColumnIndexOrThrow(VIDEO_PROJECTION[0]));
            if (TextUtils.isEmpty(path) || !new File(path).exists()) {
                //过滤不存在的
                continue;
            }
            String name = cursor.getString(cursor.getColumnIndexOrThrow(VIDEO_PROJECTION[1]));
            long dateTime = cursor.getLong(cursor.getColumnIndexOrThrow(VIDEO_PROJECTION[2]));
            String ext = cursor.getString(cursor.getColumnIndexOrThrow(IMAGE_PROJECTION[3]));
            long size = cursor.getLong(cursor.getColumnIndexOrThrow(VIDEO_PROJECTION[4]));
            int duration = cursor.getInt(cursor.getColumnIndexOrThrow(VIDEO_PROJECTION[6]));

            MediaFolder viewMediaFolder = getFolder(path, mVideoFolderList);
            MediaFolder allMediaFolder = getFolder(path, mFolderList);

            File parent = new File(path).getParentFile();
            MediaBean mediaBean = new MediaBean(path, name, dateTime, false, size, duration, parent.getPath(), getExt(name));
            viewMediaFolder.addBean(mediaBean);
            allMediaFolder.addBean(mediaBean);
//            mVideoFolderList.add(mediaBean);
        }
        cursor.close();

//        BiFunction<MediaFolder, MediaFolder, MediaFolder> biFunction = (mediaFolder1, mediaFolder2) -> {
//            if (mediaFolder1.folderPath.equals(mediaFolder2.folderPath)) {
//                Logger.i("heaven11" + mediaFolder1.folderPath + ":" + mediaFolder2.folderPath);
//                mediaFolder1.mediaList.addAll(mediaFolder2.mediaList);
//            }
//            return mediaFolder1;
//        };
//        Flowable.combineLatest(Flowable.fromIterable(mImageFolderList), Flowable.fromIterable(mVideoFolderList), biFunction)
//                .subscribe(mediaFolder -> {
//                            mFolderList.add(mediaFolder);
//                        }
//                );
    }

    //媒体库刷新
    private void refreshMedia() {
        ArrayList<MediaFolder> oldMediaFolders = new ArrayList<>(mFolderList.size());
        Collections.copy(oldMediaFolders, mFolderList);
        startScan(false);

    }

    private MediaFolder getFolder(String path, List<MediaFolder> imageFolders) {
        File imageFile = new File(path);
        File folderFile = imageFile.getParentFile();

        for (MediaFolder folder : imageFolders) {
            if (folder.folderName.equals(folderFile.getName())) {
                return folder;
            }
        }
        MediaFolder newFolder = new MediaFolder();
        newFolder.folderName = folderFile.getName();
        newFolder.folderPath = folderFile.getAbsolutePath();
        newFolder.indexPath = path;
        imageFolders.add(newFolder);
        return newFolder;
    }

    private static String getExt(String name) {
        String ext = "";
        if (!TextUtils.isEmpty(name) && name.contains(".")) {
            ext = name.substring(name.indexOf("."));
        }
        return ext;
    }

    public List<MediaFolder> getFolders() {
        return mFolderList;
    }

    public interface LocalMediaLoadListener {
        void loadComplete(List<MediaFolder> folders);
    }
}
