package com.heaven.news.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.heaven.base.ui.fragment.BaseBindFragment;
import com.heaven.news.R;
import com.heaven.news.databinding.MineBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.engine.DataCore;
import com.heaven.news.ui.vm.viewmodel.MainViewModel;

/**
 * FileName: com.heaven.news.ui.fragment.Home.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-17 14:16
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class Mine extends BaseBindFragment<MainViewModel,MineBinding> implements Observer<DataCore.CoreDataWrapper> {

    @Override
    public void bindModel() {

    }

    @Override
    public int initLayoutResId() {
        return R.layout.mine;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        AppEngine.instance().dataCore().registerDataTypeObaserver(this, this);
        DataCore.CoreDataWrapper coreDataWrapper = AppEngine.instance().dataCore().getCoreDataWrapper();
        initUserInfo(coreDataWrapper);
    }


    private void initUserInfo(DataCore.CoreDataWrapper coreDataWrapper) {
        if(coreDataWrapper != null) {
            mViewBinding.userName.setText(coreDataWrapper.userName);
            if(coreDataWrapper.sexHeaderRes != 0) {
                mViewBinding.userSexHeader.setImageResource(coreDataWrapper.sexHeaderRes);
            }
            if(coreDataWrapper.ffpIdentifyRes != 0) {
                mViewBinding.identifyType.setImageResource(coreDataWrapper.ffpIdentifyRes);
            }

            if(coreDataWrapper.cardLevelRes != 0) {
                mViewBinding.cardInfo.setText(String.format(getString(R.string.card_info_fomat),getString(coreDataWrapper.cardLevelRes),coreDataWrapper.phoenixNumber));
            } else {
                mViewBinding.cardInfo.setText(String.format(getString(R.string.card_info_fomat),"", TextUtils.isEmpty(coreDataWrapper.phoenixNumber)? "":coreDataWrapper.phoenixNumber));
            }

            mViewBinding.accountLeft.setText(coreDataWrapper.walletLeftMoney);
            mViewBinding.ecardNum.setText(coreDataWrapper.ecardNum);
            mViewBinding.couponCount.setText(coreDataWrapper.couponCount);
            mViewBinding.mile.setText(coreDataWrapper.userMile);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    public static  Mine newInstance(Bundle paramBundle) {
        Mine fragment = new Mine();
        fragment.setArguments(paramBundle);
        return fragment;
    }

    @Override
    public void onChanged(@Nullable DataCore.CoreDataWrapper coreDataWrapper) {
        if(coreDataWrapper != null) {
            if (DataCore.LOGIN == coreDataWrapper.dataType) {
                initUserInfo(coreDataWrapper);
            } else if(DataCore.MINE == coreDataWrapper.dataType) {
                initUserInfo(coreDataWrapper);
            } else if(DataCore.MILE == coreDataWrapper.dataType) {
                initUserInfo(coreDataWrapper);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppEngine.instance().dataCore().removeForeverObserve(this);
    }
}
