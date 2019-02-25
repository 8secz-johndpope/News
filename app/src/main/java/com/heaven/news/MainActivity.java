package com.heaven.news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.heaven.base.ui.activity.BaseBindActivity;
import com.heaven.news.databinding.ActivityMainBinding;
import com.heaven.news.pt.TestPt;
import com.heaven.news.vm.TestViewModel;

public class MainActivity extends BaseBindActivity<TestPt,TestViewModel, ActivityMainBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int initLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

}
