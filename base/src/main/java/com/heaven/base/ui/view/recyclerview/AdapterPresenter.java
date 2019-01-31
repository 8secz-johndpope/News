package com.heaven.base.ui.view.recyclerview;


import com.heaven.base.ui.Config;
import com.heaven.base.ui.adapter.viewholder.BaseLoadMoreFooter;
import com.heaven.base.ui.funinterface.IDbData;
import com.heaven.base.ui.funinterface.INetRemoteData;
import com.heaven.base.ui.funinterface.IServiceMediaData;
import com.heaven.model.ifilm.PagerResult;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * FileName: com.heaven.fly.base.ui.AdapterPresenter.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2017-03-19 20:49
 *
 * @version V1.0 adapter数据源
 */
public class AdapterPresenter<M> {
    private HashMap<String, Object> param = new HashMap<>();//设置远程网络仓库钥匙

    private String[] params;

    private INetRemoteData mNetRemoteData;//远程网络数据函数接口

    private IDbData mDbData;//本地数据接口

    private IServiceMediaData mServiceMedia;//后台服务接口

    private int begin = 0;

    private final IAdapterView<M> view;

    private int pageCount = 10;

    interface IAdapterView<M> {
        void setEmpty();

        void setNetData(List<M> data, int begin);

        void setDBData(List<M> data);

        void setServiceData(List<M> data);

        void reSetEmpty();

        void setLimitTotal(int limitTotal);

        void footerState(int state);

        void headerState(int state);
    }

    AdapterPresenter(IAdapterView<M> IAdapterViewImpl) {
        this.view = IAdapterViewImpl;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public HashMap<String, Object> getParam() {
        return param;
    }

    public AdapterPresenter setNetRepository(INetRemoteData netRemoteData) {
        this.mNetRemoteData = netRemoteData;
        return this;
    }

    public AdapterPresenter setParam(String key, Object value) {
        this.param.put(key, value);
        return this;
    }

    public AdapterPresenter setParams(String... params) {
        this.params = params;
        return this;
    }

    public AdapterPresenter setDbRepository(IDbData dbData) {
        this.mDbData = dbData;
        return this;
    }

    public AdapterPresenter setServiceRepository(IServiceMediaData media) {
        this.mServiceMedia = media;
        return this;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public void fetch() {
        begin++;
        if(params != null && params.length >=2) {
            params[1] = String.valueOf(begin);
        }
        view.reSetEmpty();
        if (mNetRemoteData != null) {
            view.footerState(BaseLoadMoreFooter.STATE_LOADING);
            mNetRemoteData
                    .getData(params)
                    .subscribe(
                            res -> {
                                view.setLimitTotal(((PagerResult)res.data).getPage().getPagination().getTotalRows());
                                view.setNetData(((PagerResult)res.data).getList(), begin);
                            },
                            err -> getDbData());
        } else if (mServiceMedia != null) {
            getServiceData();
        }

    }


    private void getDbData() {
        if (mDbData != null)
            mDbData.getData(param).subscribe(
                    r -> view.setDBData((List<M>) r),
                    e -> view.setEmpty());
        else view.setEmpty();
    }

    private void getServiceData() {
        if (mServiceMedia != null) {
            mServiceMedia.getFolders()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                    r -> view.setServiceData((List<M>) r),
                    e -> view.setEmpty());
        } else {
            view.setEmpty();
        }
    }
}
