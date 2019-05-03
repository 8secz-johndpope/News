/*
 * Copyright 2016 drakeet. https://github.com/drakeet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.heaven.news.ui.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author drakeet
 */
public class ItemSpecialCityDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private int spanCount;
    public ItemSpecialCityDecoration(int spanCount,int space) {
        this.space = space;
        this.spanCount = spanCount;
    }


    @Override
    public void getItemOffsets(
            Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int position = parent.getChildAdapterPosition(view);
        if (position >= 0) {
            int row = position / spanCount;
            int column = position % spanCount; // item column
            if(row == 0 || row == 2) {
                outRect.top = 0;
                outRect.bottom = 0;
                outRect.right = space;
            } else {
                outRect.top = space;
                outRect.bottom = space;
                outRect.right = space;
            }
        }
    }
}
