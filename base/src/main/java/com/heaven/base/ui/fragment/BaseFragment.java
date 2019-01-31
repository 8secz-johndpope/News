package com.heaven.base.ui.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heaven.base.presenter.BasePresenter;
import com.heaven.base.presenter.IView;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BaseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public abstract class BaseFragment<P extends BasePresenter, B extends ViewDataBinding> extends Fragment {
    public P mPresenter;
    public B mViewBinding;
    private OnFragmentInteractionListener mListener;

    protected abstract void initView();

    //初始化presenters，
    private void onInitPresenters() {
        Type type = this.getClass().getGenericSuperclass();
        if (this instanceof IView && type instanceof ParameterizedType) {
            Type[] typeArr = ((ParameterizedType) type).getActualTypeArguments();
            if (typeArr.length > 0) {
                mPresenter = getInstance(typeArr[0].getClass());
                if (mPresenter != null) {
                    mPresenter.setView(this,getContext());
                }
            }
        }
    }

    protected P getInstance(Class<?> clazz) {
        try {
            return (P)clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取layout的id，具体由子类实现
     *
     * @return
     */
    protected abstract int initLayoutResId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onInitPresenters();
        if (getArguments() != null) {
            parseBundleArgument(getArguments());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewBinding = DataBindingUtil.inflate(inflater,initLayoutResId(), container, false);

        return mViewBinding.getRoot();
    }

    protected abstract void parseBundleArgument(Bundle paramBundle);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
