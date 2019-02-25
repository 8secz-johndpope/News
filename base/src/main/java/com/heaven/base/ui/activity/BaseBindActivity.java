package com.heaven.base.ui.activity;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.heaven.base.presenter.BasePresenter;
import com.heaven.base.presenter.IView;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * FileName: com.heaven.base.ui.activity.BaseBindActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-25 13:50
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
@SuppressWarnings("unchecked")
public abstract class BaseBindActivity<P extends BasePresenter, VM extends BaseViewModel<P>, B extends ViewDataBinding> extends AppCompatActivity implements IBaseActivity {
    public B mViewBinding;
    public VM mViewModel;
    private P mPresenter;

    private void initPresenter(Class pt) {

    }

    private void initViewModel(Class vm) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        analyseGenerics();
    }

    /**
     * 范型参数解析
     */
    private void analyseGenerics() {
        Type type = this.getClass().getGenericSuperclass();
        if (this instanceof IView && type instanceof ParameterizedType) {
            Type[] typeArr = ((ParameterizedType) type).getActualTypeArguments();
            if (typeArr.length > 0) {
                for (Type clazzType : typeArr) {
                    Class clazz = (Class) clazzType;
                    String presentName = BasePresenter.class.getName();
                    String baseViewModelName = BaseViewModel.class.getName();
                    String clazzName = "";
                    if (clazz.getSuperclass() != null) {
                        clazzName = clazz.getSuperclass().getName();
                    }

                    if (clazzName.equals(presentName)) {
                        mPresenter = getInstance(clazz);
                    } else if (clazzName.equals(baseViewModelName)) {
                        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
                        mViewModel = (VM) ViewModelProviders.of(this, factory).get(clazz);
                        mViewModel.setPresenter(mPresenter);
                    }
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
}
