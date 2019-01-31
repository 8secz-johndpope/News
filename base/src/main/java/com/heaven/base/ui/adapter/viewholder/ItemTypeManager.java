package com.heaven.base.ui.adapter.viewholder;

import android.support.annotation.NonNull;
import android.util.ArrayMap;
import android.util.SparseArray;

import com.heaven.base.ui.adapter.item.ItemManager;

/**
 * 作者:Heaven
 * 时间: on 2017/4/14 09:47
 * 邮箱:heavenisme@aliyun.com
 */

public class ItemTypeManager {
    private SparseArray<ViewHolderManager> viewHolderManagers = new SparseArray<>();
    private ArrayMap<String, ViewHolderManagerGroup> viewHolderGroupManagers = new ArrayMap<>();
    private ArrayMap<String, Integer> bindMap = new ArrayMap<>();

    public void register(@NonNull ViewHolderManager manager) {
        register(manager.getItemLayoutId(), manager);
        bindMap.put(manager.getLayoutBindModel().getName(), manager.getItemLayoutId());
    }

    public void register(@NonNull ViewHolderManagerGroup group) {
        ViewHolderManager[] managers = group.getViewHolderManagers();
        if (managers != null) {
            for (ViewHolderManager manager : managers) {
                register(manager.getItemLayoutId(), manager);
            }
        }
        viewHolderGroupManagers.put(group.getViewModel().getName(), group);
    }

    private void register(int hashKey, ViewHolderManager manager) {
        viewHolderManagers.put(hashKey, manager);
    }

    public int getItemType(@NonNull Object itemData) {
        String keyName = itemData.getClass().getName();
        Integer key = bindMap.get(keyName);
        int identifyKey = key == null ? 0 : key;
        if (identifyKey <= 0) {
            ViewHolderManagerGroup group = viewHolderGroupManagers.get(keyName);
            if (group != null) {
                ViewHolderManager manager = group.getViewHolderManager(itemData);
                if (manager != null) {
                    identifyKey = manager.getItemLayoutId();
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
        if (clazz != null) {
            hashKey = clazz.getName().hashCode();
        }
        return hashKey;
    }
}
