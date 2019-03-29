package com.heaven.news.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.heaven.base.ui.adapter.BaseAdapter;
import com.heaven.base.ui.fragment.BaseSimpleBindFragment;
import com.heaven.base.ui.view.widget.gridpage.PagerGridLayoutManager;
import com.heaven.base.ui.view.widget.gridpage.PagerGridSnapHelper;
import com.heaven.news.R;
import com.heaven.news.databinding.EasygoBinding;
import com.heaven.news.engine.AppEngine;
import com.heaven.news.ui.view.gridpager.PageGridView;
import com.heaven.news.ui.vm.bean.ServiceItemHolder;
import com.heaven.news.ui.vm.model.AllServiceItem;
import com.heaven.news.ui.vm.model.ServiceItem;
import com.heaven.news.ui.vm.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: com.heaven.news.ui.fragment.Home.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-17 14:16
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class EasyGo extends BaseSimpleBindFragment<MainViewModel,EasygoBinding> {
    int width;

    List<String> data = new ArrayList<>();

    {
        for (int i = 1; i <= 17; i++) {
            data.add(i + "");
        }


    }
    @Override
    public void bindModel() {

    }


    @Override
    public int initLayoutResId() {
        return R.layout.easygo;
    }

    @Override
    public void initView(View rootView) {
        super.initView(rootView);
        width = getResources().getDisplayMetrics().widthPixels / 4;
        initGrid();

    }

    private void initGrid() {
        AllServiceItem allServiceItem = AppEngine.instance().dataCore().loadAllServiceItem(getContext());
        MyAdapter adapter2 = new MyAdapter(data);
        mViewBinding.service.setAdapter(adapter2);
        mViewBinding.service.setOnItemClickListener(adapter2);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    public static  EasyGo newInstance(Bundle paramBundle) {
        EasyGo fragment = new EasyGo();
        fragment.setArguments(paramBundle);
        return fragment;
    }

    public class MyAdapter extends PageGridView.PagingAdapter<MyVH> implements PageGridView.OnItemClickListener {
        List<String> mData = new ArrayList<>();

        public MyAdapter(List<String> data) {
            this.mData.addAll(data);
        }

        @Override
        public MyVH onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_item, parent, false);
            ViewGroup.LayoutParams params = view.getLayoutParams();
            params.height = width;
            params.width = width;
            view.setLayoutParams(params);
            return new MyVH(view);
        }

        @Override
        public void onBindViewHolder(MyVH holder, int position) {
            if(TextUtils.isEmpty(mData.get(position))){
                holder.icon.setVisibility(View.GONE);
            }else{
                holder.icon.setVisibility(View.VISIBLE);
            }
            holder.tv_title.setText(mData.get(position));
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        @Override
        public List getData() {
            return mData;
        }

        @Override
        public Object getEmpty() {
            return "";
        }

        @Override
        public void onItemClick(PageGridView pageGridView, int position) {
            String gridview = "";
            if (pageGridView == pageGridView) {
                gridview = "第一个GridView";
            }

            Toast.makeText(getActivity(), gridview + " 第" + (position + 1) + "个item 被点击" + " 值：" + mData.get(position), Toast.LENGTH_SHORT).show();
        }
    }

    public static class MyVH extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public ImageView icon;
        public MyVH(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            icon= (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}
