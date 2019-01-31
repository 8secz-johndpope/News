package com.heaven.base.ui.adapter.viewholder;

/**
 * 作者:Heaven
 * 时间: on 2017/4/12 13:52
 * 邮箱:heavenisme@aliyun.com
 */

public abstract class ViewHolderManagerGroup<T> {
    private ViewHolderManager[] viewHolderManagers;
    private Class<T> modelBean;


    /**
     * @param viewHolderManagers 相同数据源对应的所有ViewHolderManager
     */
    public ViewHolderManagerGroup(Class<T> modelBean, ViewHolderManager... viewHolderManagers) {
        if (viewHolderManagers == null || viewHolderManagers.length == 0) {
            throw new IllegalArgumentException("viewHolderManagers can not be null");
        }
        this.modelBean = modelBean;
        this.viewHolderManagers = viewHolderManagers;
    }

    /**
     * 根据item数据源中的属性判断应该返回的对应viewHolderManagers的index值
     *
     * @param itemData item数据源
     * @return index值应该是在viewHolderManagers数组有效范围内
     */
    public abstract int getViewHolderManagerIndex(T itemData);

    /**
     * 根据item数据源中的属性判断应该返回的对应viewHolderManager
     *
     * @param itemData item数据源
     * @return viewHolderManager
     */
    public ViewHolderManager getViewHolderManager(T itemData) {
        int index = getViewHolderManagerIndex(itemData);
        if (index < 0 || index > viewHolderManagers.length - 1) {
            throw new IllegalArgumentException("ViewHolderManagerGroup中的getViewHolderManagerIndex方法返回的index必须在有效范围内");
        }
        return viewHolderManagers[index];
    }

    /**
     * 通过viewHolderManager获取标识字符串，为了方便查找ViewHolderManager
     *
     * @param viewHolderManager ViewHolderManager
     * @return 获取viewHolderManager标识字符串
     */
    public String getViewHolderManagerTag(ViewHolderManager viewHolderManager) {
        return viewHolderManager.getClass().getName();
    }

    public ViewHolderManager[] getViewHolderManagers() {
        return viewHolderManagers;
    }

    public Class<T> getViewModel() {
        return modelBean;
    }
}
