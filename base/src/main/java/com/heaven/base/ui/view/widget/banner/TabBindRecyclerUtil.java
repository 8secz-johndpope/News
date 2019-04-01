package com.heaven.base.ui.view.widget.banner;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * FileName: com.heaven.base.ui.view.widget.banner.TabBindRecyclerUtil.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-04-01 21:25
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class TabBindRecyclerUtil {
    private static boolean isTabSelect = false;
    public static void bind(RecyclerViewPager recyclerView, TabLayout tabLayout) {
        recyclerView.addOnPageChangedListener((oldPosition, newPosition) -> {
            if(!isTabSelect) {
                tabLayout.setScrollPosition(newPosition, 0f, true);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                isTabSelect = true;
                int position = tab.getPosition();
                recyclerView.smoothScrollToPosition(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
