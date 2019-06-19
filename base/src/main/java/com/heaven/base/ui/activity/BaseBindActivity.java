package com.heaven.base.ui.activity;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ViewDataBinding;
import android.os.Bundle;

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
public abstract class BaseBindActivity<VM extends BaseViewModel, B extends ViewDataBinding>  extends BaseActivity<B>  {
    public VM mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        analyseGenerics();
        super.onCreate(savedInstanceState);
        if(mViewModel != null) {
            mViewModel.initModel();
            bindModel();
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
                Class clazz = (Class) typeArr[0];
                ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
                mViewModel = (VM) ViewModelProviders.of(this, factory).get(clazz);
                mViewModel.application = this.getApplication();
                mViewModel.owner = this;
                mViewModel.inject();
            }
        }
    }
}
