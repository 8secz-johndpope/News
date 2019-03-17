package com.heaven.base.ui.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heaven.base.vm.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * FileName: com.heaven.base.ui.fragment.BaseBindFragment.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-02-26 10:10
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
@SuppressWarnings("unchecked")
public abstract class BaseSimpleBindFragment<VM extends BaseViewModel, B extends ViewDataBinding> extends BaseFragment<B> {
    protected VM mViewModel;
    private BaseFragment.OnFragmentInteractionListener mListener;

    /**
     * 获取layout的id，具体由子类实现
     *
     * @return 布局id
     */
    @Override
    protected abstract int initLayoutResId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseFragment.OnFragmentInteractionListener) {
            mListener = (BaseFragment.OnFragmentInteractionListener) context;
        }
    }

    private void init() {
        analyseGenerics();
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
                    String baseViewModelName = BaseViewModel.class.getName();
                    String clazzName = "";
                    if (clazz.getSuperclass() != null) {
                        clazzName = clazz.getSuperclass().getName();
                    }

                    if (clazzName.equals(baseViewModelName)) {
                        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication());
                        mViewModel = (VM) ViewModelProviders.of(this, factory).get(clazz);
                    }
                }
            }
        }
    }

}
