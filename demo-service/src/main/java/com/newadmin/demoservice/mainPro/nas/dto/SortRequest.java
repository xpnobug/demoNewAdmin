package com.newadmin.demoservice.mainPro.nas.dto;

import com.newadmin.demoservice.mainPro.nas.entity.ItemIcon;

public class SortRequest {

    private long itemIconGroupId;
    private ItemIcon[] sortItems;

    // Getter and setter methods for itemIconGroupId and sortItems

    public long getItemIconGroupId() {
        return itemIconGroupId;
    }

    public void setItemIconGroupId(long itemIconGroupId) {
        this.itemIconGroupId = itemIconGroupId;
    }

    public ItemIcon[] getSortItems() {
        return sortItems;
    }

    public void setSortItems(ItemIcon[] sortItems) {
        this.sortItems = sortItems;
    }
}

