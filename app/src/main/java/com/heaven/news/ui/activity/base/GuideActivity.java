package com.heaven.news.ui.activity.base;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.heaven.base.ui.activity.BaseBindActivity;
import com.heaven.news.R;
import com.heaven.news.consts.RouterUrl;
import com.heaven.news.databinding.GuideBinding;
import com.heaven.news.ui.adapter.CardTransformer;
import com.heaven.news.ui.adapter.GuidePageAdapter;
import com.heaven.news.ui.vm.vmmodel.GuideNewModel;

import java.util.ArrayList;

/**
 * FileName: com.heaven.news.ui.activity.base.GuideActivity.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-08 11:29
 *
 * @author heaven
 * @version V1.0 新用户首次进入的引导页
 */
@Route(path = RouterUrl.ROUTER_URL_GUIDE)
public class GuideActivity extends BaseBindActivity<GuideNewModel, GuideBinding> {
    @Override
    public int initLayoutResId() {
        return R.layout.guide;
    }

    @Override
    protected void initMmersionTitleBar() {
    }

    @Override
    public void makeContentView(View rootView) {
        setContentView(rootView);
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        initGuide();
    }

    private void initGuide() {
        ArrayList<Integer> guidePicList = getResourceId(R.array.guide_img);
        GuidePageAdapter pagerAdapter = new GuidePageAdapter(this);
        mViewBinding.guidViewpager.setAdapter(pagerAdapter);
        mViewBinding.guidViewpager.setPageTransformer(true, new CardTransformer());
        pagerAdapter.setItemClickListener(v -> {
            if(R.id.trynow == v.getId()) {
                ARouter.getInstance().build(RouterUrl.ROUTER_URL_MAIN).navigation();
                finish();
            }
        });
        mViewBinding.indicators.setViewPager(mViewBinding.guidViewpager, guidePicList.size());
        pagerAdapter.updatePagerData(guidePicList);
    }

    @Override
    public void bindModel() {
        mViewBinding.setGuideNew(this);
    }

    private ArrayList<Integer> getResourceId(int arrayId) {
        Resources res = getResources();
        TypedArray ar = res.obtainTypedArray(arrayId);
        int len = ar.length();
        ArrayList<Integer> resIdList = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            resIdList.add(i,ar.getResourceId(i, 0));
        }
        ar.recycle();
        return resIdList;
    }
}
