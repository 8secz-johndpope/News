package com.heaven.base.ui.adapter.item;


import com.heaven.base.ui.adapter.viewholder.BaseViewHolder;
import com.heaven.base.ui.adapter.viewholder.ViewHolderManager;

/**
 * 唯一Item
 * <p>
 * getItemTypeName时返回toString作为唯一标示，使得本item对应的ViewHolderManager不可复用
 */
public class UniqueItemManager implements ItemManager {
    private ViewHolderManager manager;

    public UniqueItemManager(ViewHolderManager manager) {
        this.manager = manager;
    }

    @Override
    public String getItemTypeName() {
        return toString();
    }

    @Override
    public ViewHolderManager getViewHolderManager() {
        return manager;
    }
}
