// IRemoteService.aidl
package com.heaven.service.aidl;

// Declare any non-default types here with import statements
import com.heaven.service.aidl.IMediaCallBack;
import com.heaven.service.aidl.IChatCallBack;
import com.heaven.service.aidl.ChatMessage;
import com.heaven.service.aidl.MediaBean;
import com.heaven.service.aidl.MediaFolder;
interface IRemoteService {
    void registerMediaListener(IMediaCallBack callback);
    void unregisterMediaListener(IMediaCallBack callback);

    void registerChatListener(IChatCallBack callback);
    void unregisterChatListener(IChatCallBack callback);

    void join(IBinder token, String name);
    void leave(IBinder token);
    List<String> getParticipators();

    List<MediaFolder> getMediaFolder();

    void sendChatMessage(in ChatMessage message);

    void startUploadBatch(inout List<MediaFolder> folders);

    void startUpload(inout MediaBean data);

    void pauseUpload(inout MediaBean data);

    void cancelUpload(inout MediaBean data);

    void startScan();
}
