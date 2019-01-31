package com.heaven.data.fileworker;

import android.util.Log;

import com.heaven.data.dbentity.DownEntity;
import com.orhanobut.logger.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.CountDownLatch;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * FileName: com.heaven.data.fileworker.DownLoadTask.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-08-15 22:11
 *
 * @version V1.0 下载人物
 */
public class DownLoadTask implements Runnable {
    private HttpUpDownService mHttpUpDownService;
    private DownEntity mDownLoadEntity;
    private DownLoadWorker.DownLoadListener mDownLoadListener;
    private CountDownLatch mCountDownLatch;

    public DownLoadTask(HttpUpDownService httpUpDownService, DownEntity downLoadEntity, CountDownLatch countDownLatch, DownLoadWorker.DownLoadListener downLoadListener) {
        this.mHttpUpDownService = httpUpDownService;
        this.mDownLoadEntity = downLoadEntity;
        this.mDownLoadListener = downLoadListener;
        this.mCountDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        Logger.v(mDownLoadEntity.mSaveFilePath + ":" + Thread.currentThread().getName()  );
        if (mDownLoadEntity.mIsFinish) {
            mCountDownLatch.countDown();
        } else {
            Flowable<ResponseBody> res = mHttpUpDownService.download(mDownLoadEntity.url, "bytes=" + mDownLoadEntity.mStartPosition + "-" + mDownLoadEntity.mEndPosition);
            res.subscribeOn(Schedulers.io())
                    .onErrorReturn(throwable -> onError())
                    .subscribe(this::saveFile);
        }
    }

    private void saveFile(ResponseBody responseBody) {
        String savePath = mDownLoadEntity.mSaveFilePath;
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        RandomAccessFile file = null;
        try {
            long totalSize = mDownLoadEntity.mEndPosition;
            long mCurrentSize = mDownLoadEntity.mStartPosition;
            file = new RandomAccessFile(savePath, "rw");
            file.seek(mCurrentSize);
            fileOutputStream = new FileOutputStream(savePath, true);
            inputStream = responseBody.byteStream();
            byte[] buffer = new byte[2048];
            int len;

            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                fileOutputStream.write(buffer, 0, len);
//                file.write(buffer, 0, len);
                mCurrentSize += len;
                int progress = (int) (100 * mCurrentSize / (totalSize));
                mDownLoadListener.downLoadProgress(progress);
                // 处理下载的数据
                Logger.i(mDownLoadEntity.mSaveFilePath + ":" + "read length: " + len);
            }
            fileOutputStream.close();
            mDownLoadListener.downLoadResult(DownState.FINISH);
            inputStream.close();
            fileOutputStream.close();
            Log.d("asyncGetObjectSample", "download success.");
        } catch (IOException e) {
            try {
                if(file!=null){
                    file.close();
                }

                if (inputStream != null) {
                    inputStream.close();
                }

                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            mDownLoadListener.downLoadResult(DownState.ERROR);
            e.printStackTrace();
        }finally{
            mCountDownLatch.countDown();   //使end状态减1，最终减至0
        }
    }

    private void saveFileByChannel(ResponseBody responseBody) {
        String savePath = mDownLoadEntity.mSaveFilePath;
        long mCurrentSize = mDownLoadEntity.mStartPosition;
        InputStream inputStream = responseBody.byteStream();
        FileChannel channelOut;
        RandomAccessFile randomAccessFile;
        try {
            randomAccessFile = new RandomAccessFile(savePath, "rwd");
            channelOut = randomAccessFile.getChannel();
            MappedByteBuffer mappedBuffer = channelOut.map(FileChannel.MapMode.READ_WRITE,
                    mCurrentSize,inputStream.available());
            channelOut.write(mappedBuffer);
            inputStream.close();
            channelOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private ResponseBody onError() {
        mDownLoadListener.downLoadResult(DownState.ERROR);
        mCountDownLatch.countDown();
        return null;
    }
}
