package com.heaven.data.manager;

import android.content.Context;
import android.util.ArrayMap;

import com.heaven.data.dbentity.DownEntity;
import com.heaven.data.fileworker.DownLoadWorker;
import com.heaven.data.fileworker.HttpUpDownService;

import java.util.HashMap;

/**
 * FileName: com.heaven.data.manager.FileUpDownManager.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-08-28 22:02
 *
 * @version V1.0 文件上传下载功能管理
 */
public class FileUpDownManager {
    private Context mContext;
    private HttpUpDownService mHttpUpDownService;
    private HashMap<String, DownLoadWorker> managerMap = new HashMap<>();
    FileUpDownManager(Context context, HttpUpDownService httpUpDownService) {
        this.mContext = context;
        this.mHttpUpDownService = httpUpDownService;
    }


    void downLoadFile(DownEntity downEntity) {
        DownLoadWorker worker = DownLoadWorker.builder(mHttpUpDownService,downEntity);
        worker.startDownLoad();
        managerMap.put(downEntity.url,worker);
    }


}
