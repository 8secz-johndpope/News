package com.heaven.service.myservice;

import android.Manifest;
import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.heaven.data.manager.DataSource;
import com.heaven.service.aidl.ChatMessage;
import com.heaven.service.aidl.IChatCallBack;
import com.heaven.service.aidl.IMediaCallBack;
import com.heaven.service.aidl.IRemoteService;
import com.heaven.service.aidl.MediaBean;
import com.heaven.service.aidl.MediaFolder;
import com.heaven.service.media.MediaManager;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: com.heaven.flybetter.service.WorkerService.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-04-08 09:24
 *
 * @version V1.0 后台服务
 */
public class WorkerService extends Service implements MediaManager.LocalMediaLoadListener {
    private static final String TAG = WorkerService.class.getSimpleName();
    public static String SERVICE_FIRST_START_KEY = "com.heaven.workerService";
    // 要申请的权限
    private String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
    protected Handler mainThread = new Handler(Looper.getMainLooper());
    private List<Client> mClients = new ArrayList<>();

    private RemoteCallbackList<IMediaCallBack> mMediaCalls = new RemoteCallbackList<>();
    private RemoteCallbackList<IChatCallBack> mChatCallbacks = new RemoteCallbackList<>();

    private DataSource dataSource;
    private MediaManager mediaManager;
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        mediaManager = new MediaManager(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null) {
            String baseNetUrl = intent.getStringExtra("url");
            dataSource = DataSource.getInstance(this,baseNetUrl);
            Logger.i("onStartCommand---" + baseNetUrl);
        }
        return START_STICKY;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        persistNotification();
        return mRemoteBinder;
    }

    private void persistNotification() {
        Notification notification = new Notification();
        notification.flags = Notification.FLAG_ONGOING_EVENT;
        notification.flags |= Notification.FLAG_NO_CLEAR;
        notification.flags |= Notification.FLAG_FOREGROUND_SERVICE;
        this.startForeground(1, notification);
    }

    private IRemoteService.Stub mRemoteBinder = new IRemoteService.Stub(){

        @Override
        public void join(IBinder token, String name) throws RemoteException {
            int idx = findClient(token);
            if (idx >= 0) {
                Log.d(TAG, "already joined");
                return;
            }

            Client client = new Client(token, name);

            // 注册客户端死掉的通知
            token.linkToDeath(client, 0);
            mClients.add(client);

            // 通知client加入
            notifyParticipate(client.mName, true);
        }

        @Override
        public void leave(IBinder token) throws RemoteException {
            int idx = findClient(token);
            if (idx < 0) {
                Logger.i("already leave");
                return;
            }

            Client client = mClients.get(idx);
            mClients.remove(client);

            // 取消注册
            client.mToken.unlinkToDeath(client, 0);

            // 通知client离开
            notifyParticipate(client.mName, false);
        }

        @Override
        public List<String> getParticipators() throws RemoteException {
            ArrayList<String> names = new ArrayList<>();
            for (Client client : mClients) {
                names.add(client.mName);
            }
            return names;
        }

        @Override
        public List<MediaFolder> getMediaFolder() throws RemoteException {
            return mediaManager != null? mediaManager.getFolders() : null;
        }

        @Override
        public void sendChatMessage(ChatMessage message) throws RemoteException {

        }

        @Override
        public void startUploadBatch(List<MediaFolder> folders) throws RemoteException {

        }

        @Override
        public void startUpload(MediaBean data) throws RemoteException {

        }

        @Override
        public void pauseUpload(MediaBean data) throws RemoteException {

        }

        @Override
        public void cancelUpload(MediaBean data) throws RemoteException {

        }

        @Override
        public void startScan() throws RemoteException {
            // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                // 检查该权限是否已经获取
                int i = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
                // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
                if (i != PackageManager.PERMISSION_GRANTED) {
                    // 如果没有授予该权限，就去提示用户请求
                } else {
                    if(dataSource != null) {
                        dataSource.runWorkThread(() -> mediaManager.startScan(true));
                    }
                }
            } else {
                if(dataSource != null) {
                    dataSource.runWorkThread(() -> mediaManager.startScan(true));
                }
            }
        }

        @Override
        public void registerMediaListener(IMediaCallBack callback) throws RemoteException {
            mMediaCalls.register(callback);
            Log.d(TAG, "registerMediaListener");
        }

        @Override
        public void unregisterMediaListener(IMediaCallBack callback) throws RemoteException {
            mMediaCalls.unregister(callback);
            Logger.i("unregisterMediaListener");
        }

        @Override
        public void registerChatListener(IChatCallBack callback) throws RemoteException {
            mChatCallbacks.register(callback);
            Logger.i("registerChatListener");
        }

        @Override
        public void unregisterChatListener(IChatCallBack callback) throws RemoteException {
            mChatCallbacks.unregister(callback);
        }
    };

    private int findClient(IBinder token) {
        for (int i = 0; i < mClients.size(); i++) {
            if (mClients.get(i).mToken == token) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void loadComplete(final List<MediaFolder> folders) {
        Logger.i("loadComplete:" + folders.size());
        if(mRemoteBinder != null) {
            if(folders.size() > 0) {
                int count = mMediaCalls.beginBroadcast();
                for(int i=0; i<count; i++) {
                        Logger.i("CallbackCount:" + count);
                        final IMediaCallBack callBack = mMediaCalls.getBroadcastItem(i);
                        if(callBack != null) {
                            mainThread.post(() -> {
                                try {
                                    callBack.updateMediaAllFolders(folders);
                                } catch (RemoteException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                }
                mMediaCalls.finishBroadcast();
            }
        }
    }

    private final class Client implements IBinder.DeathRecipient {
        public final IBinder mToken;
        public final String mName;

        public Client(IBinder token, String name) {
            mToken = token;
            mName = name;
        }

        @Override
        public void binderDied() {
            // 客户端死掉，执行此回调
            int index = mClients.indexOf(this);
            if (index < 0) {
                return;
            }

            Log.d(TAG, "client died: " + mName);
            mClients.remove(this);

            // 通知client离开
            notifyParticipate(mName, false);
        }
    }

    private void notifyParticipate(String name, boolean joinOrLeave) {
        final int len = mChatCallbacks.beginBroadcast();
        for (int i = 0; i < len; i++) {
            try {
                // 通知回调
                mChatCallbacks.getBroadcastItem(i).onParticipate(name, joinOrLeave);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        mChatCallbacks.finishBroadcast();
    }

    @Override
    public void onDestroy() {
        mMediaCalls.finishBroadcast();
        mChatCallbacks.finishBroadcast();
        Intent intent = new Intent(this, WorkerService.class);
        intent.putExtra(WorkerService.SERVICE_FIRST_START_KEY,
                "START_FORM_SELF");
        startService(intent);

        Logger.i("service_onDestroy");
    }
}
