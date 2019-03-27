/*
 * Copyright (C) 2014 Lucas Rocha
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.heaven.news.ui.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.heaven.news.R;
import com.heaven.news.ui.vm.model.ImageInfo;


public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.SimpleViewHolder> {
    private static final int DEFAULT_ITEM_COUNT = 100;

    private final Context mContext;
    private final RecyclerView mRecyclerView;
    private  List<ImageInfo> mItems = null;
    private int mCurrentItemId = 0;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;

        public SimpleViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }

    public LayoutAdapter(Context context, RecyclerView recyclerView) {
       this(context, recyclerView, DEFAULT_ITEM_COUNT);
    }

    public LayoutAdapter(Context context, RecyclerView recyclerView, int itemCount) {
        mContext = context;
//        mItems = new ArrayList<>(itemCount);
//        for (int i = 0; i < itemCount; i++) {
//            addItem(i);
//        }

        mRecyclerView = recyclerView;
    }

    public void updateImageInfo(List<ImageInfo> list) {
        mItems = list;
        notifyDataSetChanged();
    }

    public void addItem(int position,ImageInfo imageInfo) {
        final int id = mCurrentItemId++;
        mItems.add(position, imageInfo);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public SimpleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position) {
        holder.title.setText(mItems.get(position).toString());

        final View itemView = holder.itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
            }
        });
        final ImageInfo itemId = mItems.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull SimpleViewHolder holder, int position, @NonNull List<Object> payloads) {
        holder.title.setText(mItems.get(position).toString());

        final View itemView = holder.itemView;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "", Toast.LENGTH_SHORT).show();
            }
        });
        final ImageInfo itemId = mItems.get(position);
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }
}
