package com.heaven.base.ui.adapter.item;

/**
 * 作者:Heaven
 * 时间: on 2017/4/12 14:08
 * 邮箱:heavenisme@aliyun.com
 */

public interface ItemDrag {
    /**
     * 是否可以在自己的Recycler中move
     *
     * @return boolean
     */
    boolean isCanMove();

    /**
     * 是否可以切换到其他Recycler
     *
     * @return boolean
     */
    boolean isCanChangeRecycler();

    /**
     * 是否可以开启拖动
     *
     * @return boolean
     */
    boolean isCanDrag();
}
