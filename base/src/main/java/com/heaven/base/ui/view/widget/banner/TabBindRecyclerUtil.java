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
    public static void bind(RecyclerViewPager recyclerView, TabLayout tabLayout) {
        recyclerView.addOnPageChangedListener((oldPosition, newPosition) -> {
            tabLayout.setScrollPosition(newPosition, 0f, true);
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
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
