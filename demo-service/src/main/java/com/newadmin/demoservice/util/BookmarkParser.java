package com.newadmin.demoservice.util;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.newadmin.demoservice.mainPro.colorGrad.entity.ColorGrad;
import com.newadmin.demoservice.util.BookmarkFolder.BookmarkItem;
import com.newadmin.demoservice.util.BookmarkFolder.BookmarkItem.Icon;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BookmarkParser {

    public static void main(String[] args) {
        File input = new File("E:\\beifen\\bookmarks_2024_4_10.html");
        try {
            Document doc = Jsoup.parse(input, "UTF-8");
            BookmarkBar bookmarks = parseBookmarks(doc);
            //导出json数据
            String json = com.alibaba.fastjson.JSON.toJSONString(bookmarks);
            // 将 JSON 字符串写入文件
            writeJsonToFile(json, "output.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        getGradientColors();
    }
    public static void writeJsonToFile(String json, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(json);
        }
    }

    /**
     * 获取书签json
     *
     * @param doc
     * @return
     */
    public static BookmarkBar parseBookmarks(Document doc) {
        List<BookmarkFolder> bookmarkFolders = new ArrayList<>();
        Elements elements = doc.select("body").select("dt");

        // 遍历所有 h3 元素，每个 h3 元素代表一个书签文件夹
        BookmarkBar bookmarkBar = null;
        for (Element h3 : elements.select("h3")) {
            BookmarkFolder bookmarkFolder = new BookmarkFolder();
            String folderName = h3.text();
            bookmarkFolder.setTitle(folderName);
            bookmarkFolder.setSort(0);

            //设置第一个dt标签为空
            if (folderName.equals("书签栏")) {
                continue;
            }

            List<BookmarkItem> bookmarks = new ArrayList<>();
            // 遍历当前文件夹下的所有书签项（dt 元素）
            for (Element dt : h3.nextElementSibling().select("dt")) {
                Element a = dt.selectFirst("a");
                if (a != null) {
                    BookmarkItem bookmark = new BookmarkItem();
                    //每个书签都有图标
                    Icon icon = new Icon();
                    icon.setSrc(FaviconUrl.getFaviconUrl(a.attr("href")));
                    icon.setBackgroundColor("#000000");
                    //随机设置渐变颜色
                    List<ColorGrad> colorGrads = getGradientColors();
                    //希望每个书签的颜色都不一样，所以随机选择一个颜色
                    int randomIndex = (int) (Math.random() * colorGrads.size());
                    icon.setJbColor(colorGrads.get(randomIndex).getCss());
                    icon.setItemType(2);
                    icon.setText("");
                    bookmark.setIcon(icon);
                    bookmark.setTitle(a.text());
                    bookmark.setUrl(a.attr("href"));
                    bookmark.setSort(9999);
                    bookmark.setOpenMethod(2);
                    bookmarks.add(bookmark);
                }
            }
            // 设置当前文件夹的书签列表
            bookmarkFolder.setChildren(bookmarks);
            // 添加当前文件夹到文件夹列表
            bookmarkBar = new BookmarkBar();
            bookmarkFolders.add(bookmarkFolder);
            bookmarkBar.setIcons(bookmarkFolders);
        }
        return bookmarkBar;
    }

    /**
     * 获取list 渐变颜色数据
     */
    public static List<ColorGrad> getGradientColors() {
        try {
            File jsonFile = new File("E:\\beifen\\color.json");
            JSON json = JSONUtil.readJSON(jsonFile, StandardCharsets.UTF_8);
            JSONArray jsonArray = JSONUtil.parseArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            Object list = jsonObject.get("list");
            JSONArray listDate = JSONUtil.parseArray(list);
            List<ColorGrad> colorGrads = new ArrayList<>();
            for (int i = 0; i < listDate.size(); i++) {
                ColorGrad colorGrad = new ColorGrad();
                colorGrad.setIdU(listDate.getJSONObject(i).getStr("idU"));
                colorGrad.setLike(listDate.getJSONObject(i).getStr("like"));
                //css的值中包含, 避免添加时报错
                colorGrad.setCss(listDate.getJSONObject(i).getStr("css"));
                colorGrad.setName(listDate.getJSONObject(i).getStr("name"));
                colorGrad.setContent(listDate.getJSONObject(i).getStr("content"));
                colorGrad.setFreeze(listDate.getJSONObject(i).getStr("freeze"));
                colorGrad.setTags(listDate.getJSONObject(i).getStr("tags"));
                colorGrads.add(colorGrad);
            }
            return colorGrads;
        } catch (IORuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}

