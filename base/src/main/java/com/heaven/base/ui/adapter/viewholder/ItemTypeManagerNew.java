package com.heaven.base.ui.adapter.viewholder;

import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.heaven.base.ui.adapter.item.ItemManager;

/**
 * 作者:Heaven
 * 时间: on 2017/4/14 09:47
 * 邮箱:heavenisme@aliyun.com
 */

public class ItemTypeManagerNew {
    private SparseArray<ViewHolderManager> viewHolderManagers = new SparseArray<>();
    private SparseArray<ViewHolderManagerGroup> viewHolderGroupManagers = new SparseArray<>();


    public void register(@NonNull ViewHolderManager manager) {
        register(identifyKeyId(manager.getLayoutBindModel()), manager);
    }

    public void register(@NonNull ViewHolderManagerGroup group) {
            ViewHolderManager[] managers = group.getViewHolderManagers();
            Class<?> viewModel = group.getViewModel();
            for (int i = 0, length = managers.length; i < length; i++) {
                register(getClassNameFromGroupKey(viewModel, group, managers[i]), managers[i]);
            }
            viewHolderGroupManagers.put(identifyKeyId(viewModel), group);
    }

    private int getClassNameFromGroupKey(Class<?> clazz, ViewHolderManagerGroup group, ViewHolderManager manager) {
        return (clazz.getName() + group.getViewHolderManagerTag(manager)).hashCode();
    }

    private void register(int hashKey, ViewHolderManager manager) {
        viewHolderManagers.put(hashKey, manager);
    }

    public int getItemType(@NonNull Object itemData) {
        int identifyKey = identifyKeyId(itemData.getClass());
        if(itemData instanceof ItemManager) {
            identifyKey = getItemTypeFromItemManager((ItemManager) itemData);
        } else {
            if(viewHolderManagers.indexOfKey(identifyKey) < 0) {
                ViewHolderManagerGroup group = viewHolderGroupManagers.get(identifyKey);
                if(group != null) {
                    ViewHolderManager manager = group.getViewHolderManager(itemData);
                    identifyKey = getClassNameFromGroupKey(itemData.getClass(), group, manager);
                }
            }
        }
        return identifyKey;
    }

    private int getItemTypeFromItemManager(ItemManager itemManager) {
        int identifyKey = identifyKeyId(itemManager.getClass());
        int itemType = viewHolderManagers.indexOfKey(identifyKey);
        if (itemType < 0) {
            ViewHolderManager holderManager = itemManager.getViewHolderManager();
            if (holderManager != null) {
                //自定义Item类型，事先不需要注册，若未查到对应关系，注册即可
                register(identifyKey, holderManager);
            }
        }
        return identifyKey;
    }

    public ViewHolderManager getViewHolderManager(int type) {
        return viewHolderManagers.get(type);
    }

    private int identifyKeyId(Class<?> clazz) {
        int hashKey = -1;
        if(clazz != null) {
            hashKey = clazz.getName().hashCode();
        }
        return hashKey;
    }
}
