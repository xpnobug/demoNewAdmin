package com.newadmin.demoservice.mainPro.nas.dto;

import com.newadmin.demoservice.mainPro.nas.entity.ItemIconGroup;

public class SortItemsRequest {

    private ItemIconGroup[] sortItems;

    // Getter and setter methods for sortItems

    public ItemIconGroup[] getSortItems() {
        return sortItems;
    }

    public void setSortItems(ItemIconGroup[] sortItems) {
        this.sortItems = sortItems;
    }
}