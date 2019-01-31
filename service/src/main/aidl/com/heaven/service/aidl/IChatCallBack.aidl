// IChatCallBack.aidl
package com.heaven.service.aidl;

// Declare any non-default types here with import statements
import com.heaven.service.aidl.ChatMessage;
interface IChatCallBack {
    // 用户加入或者离开的回调
    void onParticipate(String name, boolean joinOrLeave);

    void receiveMessage(inout ChatMessage message);
}
