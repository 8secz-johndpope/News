package com.heaven.news.engine.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.heaven.news.BuildConfig;
import com.heaven.service.aidl.ChatMessage;
import com.heaven.service.aidl.IChatCallBack;
import com.heaven.service.aidl.IMediaCallBack;
import com.heaven.service.aidl.IRemoteService;
import com.heaven.service.aidl.MediaBean;
import com.heaven.service.aidl.MediaFolder;
import com.heaven.service.myservice.WorkerService;
import com.orhanobut.logger.Logger;

import java.util.List;

import io.reactivex.Flowable;

/**
 * FileName: com.heaven.flybetter.engine.ServiceCore.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-04-09 15:30
 *
 * @version V1.0 后台服务启动和交互核心
 */
public class ServiceCore {
    /**
     * 后台服务.
     */
    private IRemoteService mService;
    /**
     * 是否已经绑定服务.
     */
    private boolean mIsBound = false;

    private static ServiceCore instance;


    private ServiceCore(Context context) {
        startService(context);
    }

    public static void initServiceCore(Context context) {
        if (instance == null) {
            instance = new ServiceCore(context);
        }
    }

    public static ServiceCore getInstance(Context context) {
        if (instance == null) {
            instance = new ServiceCore(context);
        }
        return instance;
    }

    /**
     * 后台服务链接接口.
     */
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(final ComponentName componentName, final IBinder iBinder) {
            try {
                mService = IRemoteService.Stub.asInterface(iBinder);
                if (mService != null) {
                    mIsBound = true;
                    mService.registerMediaListener(mediaCallBack);
                    mService.registerChatListener(chatCallBack);
                    mService.startScan();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onServiceDisconnected(final ComponentName componentName) {
            mIsBound = false;
            mService = null;
        }
    };

    /**
     * 启动绑定后台服务.
     *
     * @param app
     *         上下文
     */
    private void startService(final Context app) {
        if (!mIsBound) {
            Intent intent = new Intent(app, WorkerService.class);
            intent.putExtra("url", BuildConfig.ROOT_URL);
            app.startService(intent);
            app.bindService(intent, conn, Context.BIND_AUTO_CREATE);
        }
    }

    /******************************后台服务端接口***************************/

    private ServiceCore.UpdateMediaListener mUpdateMediaListener;

    public void setServiceMediaListener(ServiceCore.UpdateMediaListener updateMediaListener) {
        this.mUpdateMediaListener = updateMediaListener;
    }

    /**
     * 获取后台媒体数据.
     * @return 媒体数据流
     */
    public Flowable getFlowableAlbums() {
        return Flowable.fromCallable(() -> {
            if (mService != null) {
                return mService.getMediaFolder();
            }
            return null;
        });
    }

    /**
     * 媒体数据更新接口.
     */
    public interface UpdateMediaListener {

        void updateAllFolder(List<MediaFolder> folders);

        void updateDiffFolder(List<MediaFolder> folders);
    }

    /**
     * 媒体数据更新服务接口.
     */
    private IMediaCallBack mediaCallBack = new IMediaCallBack.Stub() {
        @Override
        public void updateMediaAllFolders(final List<MediaFolder> folders) throws RemoteException {
            Logger.i("updateMediaAllFolders:" + folders.size());
            if (mUpdateMediaListener != null) {
                mUpdateMediaListener.updateAllFolder(folders);
            }
        }

        @Override
        public void updateMediaRefreshDiffFolders(final List<MediaFolder> folders) throws RemoteException {
            Logger.i("updateMediaRefreshDiffFolders:" + folders.size());
            if (mUpdateMediaListener != null) {
                mUpdateMediaListener.updateDiffFolder(folders);
            }
        }

        @Override
        public void updateMedia(final MediaBean mediaBean) throws RemoteException {

        }
    };

    /**
     * 聊天通讯回调接口.
     */
    private IChatCallBack chatCallBack = new IChatCallBack.Stub() {
        @Override
        public void onParticipate(final String name, final boolean joinOrLeave) throws RemoteException {

        }

        @Override
        public void receiveMessage(final ChatMessage message) throws RemoteException {

        }
    };

    /******************************后台服务端接口***************************/
}
