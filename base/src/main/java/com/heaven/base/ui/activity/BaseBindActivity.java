package com.heaven.base.ui.activity;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

import com.heaven.base.R;
import com.heaven.base.presenter.BasePresenter;
import com.heaven.base.ui.SpUtil;
import com.heaven.base.ui.view.widget.SwipeBackLayout;
import com.heaven.base.utils.MPermissionUtils;
import com.heaven.base.vm.BaseViewModel;

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
public abstract class BaseBindActivity<P extends BasePresenter, VM extends BaseViewModel<P>, B extends ViewDataBinding> extends BaseActivity {
    protected B mViewBinding;
    protected VM mViewModel;
    private P mPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        analyseGenerics();
        if(mViewModel != null) {
            bindModel();
            mViewModel.initModel();
        }
    }

    /**
     * 范型参数解析
     */
    private void analyseGenerics() {
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
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
                        mViewModel.application = getApplication();
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

    @Override
    public void initView(View rootView) {

    }
}
