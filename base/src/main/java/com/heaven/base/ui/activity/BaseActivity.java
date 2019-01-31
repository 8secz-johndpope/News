package com.heaven.base.ui.activity;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import com.heaven.base.presenter.BasePresenter;
import com.heaven.base.presenter.IView;
import com.heaven.base.utils.MPermissionUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 作者：Heaven
 * 时间: on 2017/2/20 16:43
 * 邮箱：heavenisme@aliyun.com
 */
@SuppressWarnings("unchecked")
public abstract class BaseActivity<P extends BasePresenter, B extends ViewDataBinding> extends DataBindingActivity<B> {
    public P mPresenter;

    //初始化presenters，
    @Override
    public void onInitPresenters() {
        Type type = this.getClass().getGenericSuperclass();
        if (this instanceof IView && type instanceof ParameterizedType) {
            Type[] typeArr = ((ParameterizedType) type).getActualTypeArguments();
            if (typeArr.length > 0) {
                Class mPresenterClass = (Class) ((ParameterizedType) (this.getClass()
                        .getGenericSuperclass())).getActualTypeArguments()[0];

                mPresenter = getInstance(mPresenterClass);

                if (mPresenter != null) {
                    mPresenter.setView(this, getBaseContext());
                }
            }
        }
    }

    protected P getInstance(Class clazz) {
        try {
            return (P) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从intent中解析数据，具体子类来实现
     *
     * @param argIntent
     */
    protected void parseArgumentsFromIntent(Intent argIntent) {
    }

    @Override
    protected void onResume() {
        super.onResume();
        //依次调用IPresenter的onResume方法
        if (mPresenter != null) {
            mPresenter.onResume();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.onStart();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.onStop();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPresenter != null) {
            mPresenter.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDetached();
            mPresenter.onDestroy();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
