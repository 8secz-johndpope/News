// IMediaData.aidl
package com.heaven.service.aidl;

// Declare any non-default types here with import statements
import com.heaven.service.aidl.MediaBean;
import com.heaven.service.aidl.MediaFolder;
interface IMediaCallBack {
        void updateMediaAllFolders(in List<MediaFolder> folders);
        void updateMediaRefreshDiffFolders(in List<MediaFolder> folders);
        void updateMedia(in MediaBean mediaBean);
}
