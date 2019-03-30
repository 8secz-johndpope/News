package com.heaven.news.ui.vm.holder;

import android.support.annotation.NonNull;
import android.view.View;

import com.heaven.base.ui.adapter.viewholder.BaseMultItem;
import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.news.R;
import com.neusoft.szair.model.noticelist.noticeInfoListVO;

/**
 * FileName: com.heaven.news.ui.vm.holder.HomeNoticeHolder.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-30 18:09
 *
 * @version V1.0 TODO <描述当前版本功能>
 */
public class HomeNoticeHolder extends BaseMultItem<noticeInfoListVO> {

    public HomeNoticeHolder(@NonNull Class<noticeInfoListVO> modelBean, int itemLayoutId) {
        super(modelBean, itemLayoutId);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull noticeInfoListVO noticeInfoListVO) {
        holder.setText(R.id.notice_content,noticeInfoListVO._INFO_STITLE);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, @NonNull noticeInfoListVO noticeInfoListVO, Object payload) {

    }
}
