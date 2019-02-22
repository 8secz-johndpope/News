package com.heaven.base.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.heaven.base.vm.BaseContextViewModel;
import com.heaven.base.vm.BaseViewModel;
import android.arch.lifecycle.Observer;
import android.view.View;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * FileName: com.heaven.base.ui.BaseBindActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-21 14:05
 *
 * @version V1.0 viewbind基础类
 */
public abstract class BaseBindActivity<VM extends BaseContextViewModel, VB extends ViewDataBinding> extends AppCompatActivity implements IBaseActivity {
    protected VM mViewModel;
    protected VB mViewBind;

    @Override
    public void onInitVm() {
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] typeArr = ((ParameterizedType) type).getActualTypeArguments();
            if (typeArr.length > 1) {
                Class viewModel = (Class) ((ParameterizedType) (this.getClass()
                        .getGenericSuperclass())).getActualTypeArguments()[1];
                ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
                mViewModel = (VM) ViewModelProviders.of(this, factory).get(viewModel);
//                mViewModel.liveData.observe(this,this);
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutInflater().inflate(this.initLayoutResId(), null, false);
        mViewBind = DataBindingUtil.bind(rootView);

//        ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
    }




}
