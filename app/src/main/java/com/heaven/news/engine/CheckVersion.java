package com.heaven.news.engine;

import com.heaven.data.manager.DataSource;
import com.heaven.news.consts.Constants;
import com.heaven.news.ui.vm.model.base.VersionUpdate;
import com.heaven.news.ui.vm.model.base.Version;

/**
 * FileName: com.heaven.news.engine.CheckVersion.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-28 18:01
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class CheckVersion {

    static VersionUpdate checkVersion(Version version, DataSource dataSource) {
        VersionUpdate updateInfo = new VersionUpdate();
        if (version != null) {
            AppInfo appInfo = AppEngine.instance().getAppConfig();
            updateInfo.updateUrl = version.url;
            updateInfo.updateMessage = version.txt;
            if (version.cversion > 65534) {
                updateInfo.isServiceMainta = true;
            } else {
                if (appInfo.verCode < version.cversion) {
                    updateInfo.needUpdate = true;
                    if (appInfo.verCode < version.fversion) {
                        updateInfo.isForceUpdate = true;
                    }
                }
            }
        } else {
            updateInfo.isNetError = true;
        }
        return processNextStep(updateInfo, dataSource);
    }


    private static VersionUpdate processNextStep(VersionUpdate updateInfo, DataSource dataSource) {
        boolean isOldUser = AppEngine.instance().getDataSource().getSharePreBoolean(Constants.ISOLDUSER);
        updateInfo.nextGuidePage = !isOldUser;
        dataSource.setSharePreBoolean(Constants.ISOLDUSER, true);
        return updateInfo;
    }
}
