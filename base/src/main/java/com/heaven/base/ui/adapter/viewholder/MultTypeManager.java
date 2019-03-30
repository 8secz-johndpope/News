package com.heaven.base.ui.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import io.reactivex.annotations.NonNull;

/**
 * FileName: com.heaven.base.ui.adapter.viewholder.MultTypeManager.java
 * author: Heaven
 * email: heavenisme@aliyun.com
 * date: 2019-03-19 13:51
 *
 * @version V1.0 adapter type manage
 */
public class MultTypeManager {
    private final Map<Class<?>, CopyOnWriteArrayList<BaseMultItem>> beanToTypeListMap = new ConcurrentHashMap<>();
    private final Map<Integer, BaseMultItem> typeToMultItemMap = new ConcurrentHashMap<>();
    private final Map<BaseMultItem, Integer> multItemToTypeMap = new ConcurrentHashMap<>();
    private final Map<Integer, Integer> typeToRecycle = new ConcurrentHashMap<>();


    public <T> void register(@NonNull Class clazz, @NonNull BaseMultItem<T> multItem) {
        CopyOnWriteArrayList<BaseMultItem> typeList = beanToTypeListMap.get(clazz);
        if (typeList == null) {
            typeList = new CopyOnWriteArrayList<>();
        }
        int type = typeToMultItemMap.size();
        if (multItem.containChild()) {
            typeList.addAll(multItem.getChildList());
            for (BaseMultItem<T> baseMultItem : multItem.getChildList()) {
                bindTypeAndItem(type, baseMultItem);
                type++;
            }
        } else {
            typeList.add(multItem);
            bindTypeAndItem(type, multItem);
        }

        beanToTypeListMap.put(clazz, typeList);
    }

    private void bindTypeAndItem(Integer type, BaseMultItem item) {
        typeToMultItemMap.put(type, item);
        multItemToTypeMap.put(item, type);
    }

    public BaseMultItem getMultItemByType(int itemType) {
        return typeToMultItemMap.get(itemType);
    }

    public BaseMultItem getMultiItemView(int itemViewType) {
        return typeToMultItemMap.get(itemViewType);
    }



    public <T> int getItemType(@NonNull T item, int position) {
        int type = -1;
        CopyOnWriteArrayList<BaseMultItem> itemList = beanToTypeListMap.get(item.getClass());
        if (itemList != null) {
            for (BaseMultItem baseMultItem : itemList) {
                if (baseMultItem.isTargetViewType(item, position)) {
                    type = multItemToTypeMap.get(baseMultItem);
                    break;
                }
            }
        }
        return type;

    }

    public void setMaxRecycledViews(ViewGroup recyclerView, int itemType) {
        if (!typeToRecycle.containsKey(itemType)) {
            BaseMultItem multiItemView = typeToMultItemMap.get(itemType);
            if(multiItemView != null) {
                typeToRecycle.put(itemType, multiItemView.getMaxRecycleCount());
                ((RecyclerView) recyclerView).getRecycledViewPool().setMaxRecycledViews(itemType, multiItemView.getMaxRecycleCount());
            }
        }
    }


}
