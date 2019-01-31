package com.heaven.data.fileworker;

import android.text.TextUtils;

import com.heaven.data.dbentity.DownEntity;
import com.heaven.data.net.NetGlobalConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import io.realm.RealmList;
import okhttp3.ResponseBody;

/**
 * FileName: com.heaven.data.fileworker.DownLoadWorker.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-08-16 19:39
 *
 * @version V1.0 下载工作类
 */
public class DownLoadWorker {
    private HttpUpDownService mHttpUpDownService;
    // 开始的倒数锁
    private CountDownLatch begin = new CountDownLatch(1);
    // 结束的倒数锁
    private CountDownLatch end;

    private DownEntity mDownEntity;

    private String fileName;

    private String ext;

    private String realSaveFilePath;

    private String mUrl;

    private ArrayList<DownLoadTask> tasks = new ArrayList<>();

    private DownLoadListener mDownLoadListener;

    private DownLoadListener downLoadListener = new DownLoadListener() {
        @Override
        public void downLoadResult(DownState result) {

        }

        @Override
        public void downLoadProgress(int percent) {

        }
    };

    public static DownLoadWorker builder(HttpUpDownService httpUpDownService, DownEntity downEntity) {
        return new DownLoadWorker(httpUpDownService, downEntity);
    }

    private DownLoadWorker(HttpUpDownService httpUpDownService, DownEntity downEntity) {
        this.mHttpUpDownService = httpUpDownService;
        this.mDownEntity = downEntity;
        this.mDownLoadListener = downEntity.listener;
        initWorkerData(mDownEntity);
    }

    private void initWorkerData(DownEntity downEntity) {
        mUrl = downEntity.url;
//        String saveFolderPath = downEntity.mSaveFolderPath;
//        String saveFilePath = downEntity.mSaveFilePath;
        if (!TextUtils.isEmpty(mUrl)) {
            int lastPointPosition = mUrl.lastIndexOf(".");
            int lastNamePosition = mUrl.lastIndexOf("/");
            ext = mUrl.substring(lastPointPosition);
            fileName = mUrl.substring(lastNamePosition, lastPointPosition);
        }

        if (TextUtils.isEmpty(downEntity.mSaveFolderPath)) {
            realSaveFilePath = downEntity.mSaveFolderPath + "/" + fileName + "." + ext;
        }
    }

    public void startDownLoad() {
        tasks.clear();
        checkFileData();
    }

    public void pauseDownLoad() {

    }

    public void resumeDownLoad() {

    }

    public void stopDownLoad() {

    }

    private ResponseBody onError() {
        mDownLoadListener.downLoadResult(DownState.ERROR);
        return null;
    }

    private void checkFileData() {
        if (mHttpUpDownService != null && mDownEntity != null) {
            Flowable<ResponseBody> checkResult = mHttpUpDownService.download(mDownEntity.url, "-");

            checkResult
                    .subscribeOn(Schedulers.io())
                    .onErrorReturn(throwable -> onError())
                    .subscribe(this::checkValidateData
                    );
        }
    }

    private void checkValidateData(ResponseBody responseBody) {
//        Headers headers = responseBody.headers();
//        String last_modified = headers.get("Last-Modified");
        long contentTotalLength = responseBody.contentLength();

        if (contentTotalLength > 0) {
            boolean isOldTask = (contentTotalLength == mDownEntity.countLength);
//            if (!isOldTask) {
            mDownEntity.countLength = contentTotalLength;
//            mDownEntity.setLast_Modified(last_modified);
//            }

            initTasks(mDownEntity, isOldTask);
            startMultDownLoad();
            try {
                end.await();
                assembleFile();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void startMultDownLoad() {
        if (tasks.size() > 0) {
            Flowable.fromIterable(tasks).subscribe(DownLoadTask::run);
        }
    }


    private void initTasks(DownEntity downEntity, boolean isOld) {
        int partNum = (int) Math.ceil(mDownEntity.countLength / NetGlobalConfig.MULTPARTSIZE);
        RealmList<DownEntity> multTaskInfoList = downEntity.multTaskList;
        if (multTaskInfoList == null) {
            multTaskInfoList = new RealmList<>();
        }
        int oldTaskSize = multTaskInfoList.size();
        end = new CountDownLatch(partNum);
        for (int i = 1; i <= partNum; i++) {
            if (i <= oldTaskSize) {
                DownEntity oldTaskEntity = multTaskInfoList.get(i - 1);
                if (!oldTaskEntity.mIsFinish) {
                    oldTaskEntity.reStoreSceneData();
                    DownLoadTask downLoadTask = new DownLoadTask(mHttpUpDownService, oldTaskEntity, end, downLoadListener);
                    tasks.add(downLoadTask);
                } else {
                    end.countDown();
                }
            } else {
                long startPosition;
                long endPosition;
                if (i == 1) {
                    startPosition = 0;
                    if (partNum == 1) {
                        endPosition = mDownEntity.countLength;

                    } else {
                        endPosition = NetGlobalConfig.MULTPARTSIZE;
                    }
                } else if (i > 1 && i < partNum) {
                    startPosition = (i - 1) * NetGlobalConfig.MULTPARTSIZE;
                    endPosition = i * NetGlobalConfig.MULTPARTSIZE;
                } else {
                    startPosition = (i - 1) * NetGlobalConfig.MULTPARTSIZE;
                    endPosition = downEntity.countLength - 1;
                }
                DownEntity newTaskEntity = new DownEntity(false, i, downEntity.url, downEntity.mSaveFolderPath, startPosition, endPosition);
                multTaskInfoList.add(newTaskEntity);

                DownLoadTask downLoadTask = new DownLoadTask(mHttpUpDownService, newTaskEntity, end, downLoadListener);
                tasks.add(downLoadTask);
            }
        }
        downEntity.multTaskList = multTaskInfoList;
    }


    //文件合并的方法（传入要合并的文件路径）
    private void assembleFile() {
        if (mDownEntity != null) {
            RealmList<DownEntity> entities = mDownEntity.multTaskList;
            if (entities != null && entities.size() > 0) {
                String mainFilePath = mDownEntity.mSaveFilePath;
                if (!TextUtils.isEmpty(mainFilePath)) {
                    FileOutputStream out = null;
                    FileChannel outChannel = null;
                    try {
                        out = new FileOutputStream(mainFilePath, true);
                        out.getChannel();
                        for (DownEntity entity : entities) {
                            File file = new File(entity.mSaveFilePath);
                            //读取小文件的输入流
                            InputStream in = new FileInputStream(file);
                            int len;
                            byte[] bytes = new byte[10 * 1024 * 1024];
                            while ((len = in.read(bytes)) != -1) {
                                out.write(bytes, 0, len);
                            }
                            in.close();
                        }
                        deleteTempFile(entities);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if(out != null) {
                            try {
                                out.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        System.out.println("文件合并完成！");
    }

    private void deleteTempFile(RealmList<DownEntity> entities) {
        if (entities != null && entities.size() > 0) {
            for(DownEntity entity : entities) {
                String tempFilePath = entity.mSaveFilePath;
                if(!TextUtils.isEmpty(tempFilePath)) {
                    File file = new File(tempFilePath);
                    if(file.exists() && file.isFile()) {
                        file.delete();
                    }
                }
            }
        }
    }

    /**
     * 通过RandomAccessFile方式
     * @param src 文件源
     */
    public void mergeFiles(String... src) {
        try {
            File realFile = new File(mDownEntity.mSaveFilePath);
            FileOutputStream fos = new FileOutputStream(realFile);
            RandomAccessFile ra = null;
            for (int i = 0; i < src.length; i++) {
                ra = new RandomAccessFile(src[i], "r");
                if (i != 0) {
                    ra.seek(6);//跳过amr文件的文件头
                }
                byte[] buffer = new byte[1024 * 8];
                int len;
                while ((len = ra.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                }
            }
            ra.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 使用文件通道的方式复制文件
     *
     * @param fromFile 源文件
     * @param destFile 复制到的新文件
     */
    public void fileChannelCopy(File destFile, File fromFile) {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(fromFile);
            fo = new FileOutputStream(destFile);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public interface DownLoadListener {
        void downLoadResult(DownState result);

        void downLoadProgress(int percent);
    }

}
