package com.heaven.data.dbentity;

import android.text.TextUtils;

import com.heaven.data.fileworker.DownLoadWorker;
import com.heaven.data.fileworker.DownState;
import com.heaven.data.util.MD5Util;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * apk下载请求数据基础类
 * Created by WZG on 2016/10/20.
 */

public class DownEntity extends RealmObject {
    /*url*/
    @PrimaryKey
    @Index
    public String url;

    public String fileName;

    public String ext;

    public long id;

    /*文件夹存储位置*/
    public String mSaveFolderPath;
    /*文件存储位置*/
    public String mSaveFilePath;
    /*文件总长度*/
    public long countLength;
    /*下载长度*/
    public long readLength;
    /*下载的起始位置*/
    public long mStartPosition;
    /*下载的结束位置*/
    public long mEndPosition;
    /*state状态数据库保存*/
    public int state;
    /*分块子任务集合*/
    public RealmList<DownEntity> multTaskList;
    /*当前下载的总进度*/
    public int currentTotalPercent;
    /*文件是否变更*/
    public String Last_Modified;
    /* 块下载标示*/
    public int mEtag;
    /*是否是根任务*/
    public boolean mIsRoot;
    /*是否已经下载完成*/
    public boolean mIsFinish;

    @Ignore
    public DownLoadWorker.DownLoadListener listener;

    public DownEntity() {

    }

    public DownEntity(boolean isRoot, int etag, String url, String saveFolderPath, long startPosition, long endPosition) {
        this.mIsRoot = isRoot;
        this.mEtag = etag;
        this.mSaveFolderPath = saveFolderPath;
        this.url = url;
        this.mStartPosition = startPosition;
        this.mEndPosition = endPosition;
        initEntity(isRoot, etag, url);
    }

    public void initEntity(boolean isRoot, int etag, String url) {
        if (!TextUtils.isEmpty(url)) {
            int lastPointPosition = url.lastIndexOf(".");
            int lastNamePosition = url.lastIndexOf("/") + 1;
            ext = url.substring(lastPointPosition);
            fileName = url.substring(lastNamePosition, lastPointPosition);

            if (!TextUtils.isEmpty(mSaveFolderPath)) {
                if (isRoot) {
                    mSaveFilePath = mSaveFolderPath + fileName + ext;
                } else {
                    mSaveFilePath = mSaveFolderPath  + MD5Util.base64Encode(fileName + etag);
                }
                File file = new File(mSaveFilePath);
                if(!file.exists()) {
                    if (!file.getParentFile().exists()) {
                        if (!file.getParentFile().mkdirs()) {
                            Logger.i("创建目标文件所在目录失败！");
                            return;
                        }
                    }
                    try {
                        if (file.createNewFile()) {
                            Logger.i("创建单个文件" + fileName + "成功！");
                        } else {
                            Logger.i("创建单个文件" + fileName + "失败！");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public DownState getState() {
        switch (getCurrentState()) {
            case 0:
                return DownState.START;
            case 1:
                return DownState.DOWN;
            case 2:
                return DownState.PAUSE;
            case 3:
                return DownState.STOP;
            case 4:
                return DownState.ERROR;
            case 5:
            default:
                return DownState.FINISH;
        }
    }

    public void setState(DownState state) {
        setState(state.getState());
    }


    public int getCurrentState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void reStoreSceneData() {
        if(!TextUtils.isEmpty(mSaveFilePath)) {
            File file = new File(mSaveFilePath);
            if(file.exists() && file.isFile()) {
                mStartPosition = file.length();
            }
        }
    }
}
