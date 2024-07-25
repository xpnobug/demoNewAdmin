package com.newadmin.demoservice.util;

import java.util.List;

/**
 * 书签栏类，用于表示书签栏的信息
 */
public class BookmarkBar {
    private List<BookmarkFolder> icons;

    /**
     * 获取书签栏图标列表
     * @return 书签栏图标列表
     */
    public List<BookmarkFolder> getIcons() {
        return icons;
    }

    /**
     * 设置书签栏图标列表
     * @param icons 书签栏图标列表
     */
    public void setIcons(List<BookmarkFolder> icons) {
        this.icons = icons;
    }
}