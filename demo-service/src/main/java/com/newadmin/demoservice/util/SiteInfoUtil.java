package com.newadmin.demoservice.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.newadmin.demoservice.mainPro.nas.entity.SiteInfo;
import java.io.IOException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SiteInfoUtil {

    public static SiteInfo getSiteInfo(String str) {
        SiteInfo siteInfo = new SiteInfo();
        try {
            String url = StrUtil.trimToEmpty(str);
            // 假设输入的参数不携带协议，则拼接协议
            if (!isHttpOrHttps(str)) {
                url = StrUtil.format("https://{}", str);
            }
            URL urlObj = new URL(url);
            // 处理出新的url
            String newUrl = urlObj.getProtocol().concat("://").concat(urlObj.getHost());
            // 发请求
            Document document = Jsoup.connect(newUrl).get();
            //获取title
            Elements title = document.select("title");
            siteInfo.setTitle(title.text());

            //<meta name="description" content="">
            Elements meta = document.select("meta[name=description]");
            siteInfo.setDescription(meta.attr("content"));
            // 筛选包含favicon图标的link标签
            Elements favicon = document.select("link[type=image/x-icon]");
            favicon = ObjectUtil.isEmpty(favicon) ? document.select("link[rel$=icon]") : favicon;
            // 获取favicon路径
            String href = favicon.attr("href");
            // 假设获取到的favicon路径已经包含了域名，则直接返回
            if (isHttpOrHttps(href) && StrUtil.containsAny(href, "favicon", "svg", "png", "jpg",
                "jpeg")) {
                siteInfo.setIconUrl(href);
            } else {
                // 假设获取到的favicon路径不包含域名，则拼接域名
                siteInfo.setIconUrl(
                    StrUtil.format("{}/{}", newUrl, StrUtil.removePrefix(href, "/")));
            }
            // 拼接favicon的访问链接
        } catch (IOException i) {
            i.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return siteInfo;
    }

    /**
     * 检测是否http或https
     *
     * @param url URL
     * @return 检验是否是http或https
     */
    public static boolean isHttpOrHttps(String url) {
        String lowerCaseStr = url.toLowerCase();
        return lowerCaseStr.startsWith("http:") || lowerCaseStr.startsWith("https:");
    }
}
