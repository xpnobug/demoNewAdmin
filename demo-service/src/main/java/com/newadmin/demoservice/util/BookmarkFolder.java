package com.newadmin.demoservice.util;

import java.util.List;

public class BookmarkFolder {

    // 文件夹标题
    private String title;
    // 排序
    private int sort;
    // 子书签列表
    private List<BookmarkItem> children;

    // 获取文件夹标题
    public String getTitle() {
        return title;
    }

    // 设置文件夹标题
    public void setTitle(String title) {
        this.title = title;
    }

    // 获取排序
    public int getSort() {
        return sort;
    }

    // 设置排序
    public void setSort(int sort) {
        this.sort = sort;
    }

    // 获取子书签列表
    public List<BookmarkItem> getChildren() {
        return children;
    }

    // 设置子书签列表
    public void setChildren(List<BookmarkItem> children) {
        this.children = children;
    }

    // 书签项类
    public static class BookmarkItem {
        // 图标信息
        private Icon icon;
        // 排序
        private int sort;
        // 标题
        private String title;
        // URL
        private String url;
        // 打开方法
        private int openMethod;
        // 语言 URL
        private String lanUrl;
        // 描述
        private String description;

        // 获取图标信息
        public Icon getIcon() {
            return icon;
        }

        // 设置图标信息
        public void setIcon(Icon icon) {
            this.icon = icon;
        }

        // 获取排序
        public int getSort() {
            return sort;
        }

        // 设置排序
        public void setSort(int sort) {
            this.sort = sort;
        }

        // 获取标题
        public String getTitle() {
            return title;
        }

        // 设置标题
        public void setTitle(String title) {
            this.title = title;
        }

        // 获取URL
        public String getUrl() {
            return url;
        }

        // 设置URL
        public void setUrl(String url) {
            this.url = url;
        }

        // 获取打开方法
        public int getOpenMethod() {
            return openMethod;
        }

        // 设置打开方法
        public void setOpenMethod(int openMethod) {
            this.openMethod = openMethod;
        }

        // 获取语言 URL
        public String getLanUrl() {
            return lanUrl;
        }

        // 设置语言 URL
        public void setLanUrl(String lanUrl) {
            this.lanUrl = lanUrl;
        }

        // 获取描述
        public String getDescription() {
            return description;
        }

        // 设置描述
        public void setDescription(String description) {
            this.description = description;
        }

        // 图标信息类
        public static class Icon {
            // 文本
            private String text;
            // 类型
            private int itemType;
            // 图标地址
            private String src;
            // 背景颜色
            private String backgroundColor;
            private String jbColor;

            // 获取文本
            public String getText() {
                return text;
            }

            // 设置文本
            public void setText(String text) {
                this.text = text;
            }

            // 获取类型
            public int getItemType() {
                return itemType;
            }

            // 设置类型
            public void setItemType(int itemType) {
                this.itemType = itemType;
            }

            // 获取图标地址
            public String getSrc() {
                return src;
            }

            // 设置图标地址
            public void setSrc(String src) {
                this.src = src;
            }

            // 获取背景颜色
            public String getBackgroundColor() {
                return backgroundColor;
            }

            // 设置背景颜色
            public void setBackgroundColor(String backgroundColor) {
                this.backgroundColor = backgroundColor;
            }

            public String getJbColor() {
                return jbColor;
            }

            public void setJbColor(String jbColor) {
                this.jbColor = jbColor;
            }
        }
    }
}
