package com.heaven.base.ui.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heaven.base.presenter.BasePresenter;
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
public abstract class BaseBindFragment<P extends BasePresenter, VM extends BaseViewModel<P>, B extends ViewDataBinding> extends BaseFragment<B> {
    protected VM mViewModel;
    private P mPresenter;
    private BaseFragment.OnFragmentInteractionListener mListener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        analyseGenerics();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if(mViewModel != null) {
            bindModel();
            mViewModel.initModel();
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseFragment.OnFragmentInteractionListener) {
            mListener = (BaseFragment.OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindModel();
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
                        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication());
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
