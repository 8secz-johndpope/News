package com.heaven.base.presenter;


import android.content.Context;
import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 作者：Heaven
 * 时间: on 2017/2/24 17:57
 * 邮箱：heavenisme@aliyun.com
 */

public abstract class BasePresenter<V> {
    protected V mView;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void setView(@NonNull V v, @NonNull Context context) {
        this.mView = v;
        this.onAttached();
        initInject();
    }

    public abstract void initInject();

    public void onAttached(){

    }

    public void onDetached() {
        mCompositeDisposable.dispose();
    }

    public void onStop(){
        mCompositeDisposable.dispose();
    }

    public void onResume(){

    }

    public void onDestroy(){
        mCompositeDisposable.dispose();
    }

    public void onPause(){

    }

    public void onStart(){

    }


    protected void addDisposable(Disposable disposable){
        if (mCompositeDisposable != null) {
            mCompositeDisposable.add(disposable);
        }
    }

}
