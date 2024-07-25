package com.newadmin.demoservice.mainPro.qqbots.base.config;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.newadmin.demoservice.mainPro.qqbots.base.intents.Intents;
import com.newadmin.demoservice.mainPro.qqbots.base.intents.IntentsCalculator;
import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.Random;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;

/**
 * api事件
 *
 * @author 86136
 */
public class BotEvent {

    /**
     * 发送消息
     *
     * @param url
     * @param msgJson
     */
    public static void sendMsg(String url, JSONObject msgJson) {
        final HttpResponse res = HttpRequest.post(url)
            .connectionTimeout(BaseFinal.CONNECTION_TIMEOUT)
            .timeout(BaseFinal.REQUEST_TIMEOUT)
            .header("Content-Type", "application/json")
            .header("Authorization", BaseFinal.Bot_Token)
            .bodyText(String.valueOf(msgJson), "application/json")
            .send();

        if (HttpServletResponse.SC_OK != res.statusCode()) {
            System.out.println("消息发送失败:" + res.bodyText());
        } else {
            System.out.println("消息发送成功:" + String.valueOf(msgJson));
        }
    }

    /**
     * 获取访问令牌
     *
     * @return 访问令牌字符串
     */
    public static String getAccessToken() {
        // 创建请求主体
        JSONObject requestBody = new JSONObject();
        requestBody.put("appId", "102072731");
        requestBody.put("clientSecret", "AF7lALI0Tr20kOqD");

        // 发送 POST 请求
        final HttpResponse res = HttpRequest.post("https://bots.qq.com/app/getAppAccessToken")
            .connectionTimeout(20000) // 连接超时：20秒
            .timeout(60000) // 请求超时：60秒
            .header("Content-Type", "application/json") // 设置请求头
            .body(requestBody.toString()) // 设置请求主体
            .send();

        // 检查响应状态码
        if (HttpServletResponse.SC_OK != res.statusCode()) {
            System.out.println("false");
        }
        // 设置响应的字符集
        res.charset("UTF-8");
        // 获取响应体文本
        String responseText = res.bodyText();
        JSONObject jsonObject = new JSONObject(responseText);
        return "QQBot " + jsonObject.getStr("access_token");
    }

    public static void main(String[] args) {
        System.out.println(getAccessToken());
    }

    /**
     * 获取带分片 WSS 接入点
     */
    public static JSONObject fetchGatewayBotInfo() {
        String accessToken = getAccessToken();
        try {
            final HttpResponse res = HttpRequest.get(BaseFinal.GATEWAY_BOT_URL)
                .connectionTimeout(BaseFinal.CONNECTION_TIMEOUT)
                .timeout(BaseFinal.REQUEST_TIMEOUT)
                .header("Content-Type", "application/json")
                .header("Authorization", accessToken)
                .send();

            if (HttpServletResponse.SC_OK != res.statusCode()) {
                System.out.println("请求失败，状态码：" + res.statusCode());
            } else {
                String responseBody = res.bodyText();
                JSONObject jsonObject = new JSONObject(responseBody);

                // 获取推荐的分片数
                int recommendedShards = jsonObject.getInt("shards");
                JSONObject sessionStartLimit = jsonObject.getJSONObject("session_start_limit");
                int maxConcurrency = sessionStartLimit.getInt("max_concurrency");

                // 计算分片ID
                BigInteger guildId = new BigInteger("16093159052943820804");
                int shardId = calculateShardId(guildId, recommendedShards);

                // 设置连接的属性
                JSONObject properties = new JSONObject();
                properties.put("$os", "linux");
                properties.put("$browser", "my_library");
                properties.put("$device", "my_library");

                // 构建连接的有效载荷
                JSONObject payload = new JSONObject();
                payload.put("op", 2);

                JSONObject data = new JSONObject();
                data.put("token", accessToken);
                data.put("intents", IntentsCalculator.calculateIntents(
                    // 订阅 GUILDS 相关事件
                    // Intents.GUILDS,

                    // 订阅 GUILD_MEMBERS 相关事件
                    // Intents.GUILD_MEMBERS,

                    // 订阅 GUILD_MESSAGES 相关事件，仅限私域机器人
                    // Intents.GUILD_MESSAGES,

                    // 订阅 GUILD_MESSAGE_REACTIONS 相关事件
                    // Intents.GUILD_MESSAGE_REACTIONS,

                    // 订阅 DIRECT_MESSAGE 相关事件，当收到用户发给机器人的私信消息时触发
//                    Intents.DIRECT_MESSAGE

                    // 订阅 OPEN_FORUMS_EVENT 相关事件
                    // Intents.OPEN_FORUMS_EVENT,

                    // 订阅 AUDIO_OR_LIVE_CHANNEL_MEMBER 相关事件，当用户进入或离开音视频/直播子频道时触发
//                    Intents.AUDIO_OR_LIVE_CHANNEL_MEMBER

                    // 订阅 INTERACTION 相关事件
                    // Intents.INTERACTION,

                    // 订阅 MESSAGE_AUDIT 相关事件
                    // Intents.MESSAGE_AUDIT,

                    // 订阅 FORUMS_EVENT 相关事件，仅限私域机器人
                    // Intents.FORUMS_EVENT,

                    // 订阅 AUDIO_ACTION 相关事件
                    // Intents.AUDIO_ACTION,

                    // 订阅 PUBLIC_GUILD_MESSAGES 相关事件，此为公域消息事件
                    Intents.PUBLIC_GUILD_MESSAGES
                ));

                data.put("shard", new JSONArray(new int[]{shardId, maxConcurrency}));
                data.put("properties", properties);

                payload.put("d", data);
                return payload;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("请求或解析失败");
        }
        return null;
    }

    /**
     * 根据频道ID和推荐的分片数计算分片ID
     *
     * @param guildId   频道ID
     * @param numShards 推荐的分片数
     * @return 分片ID
     */
    private static int calculateShardId(BigInteger guildId, int numShards) {
        return guildId.shiftRight(22).mod(BigInteger.valueOf(numShards)).intValue();
    }

    /**
     * 随机唱鸭
     *
     * @return
     */
    public static String getcyApi() {
        final HttpResponse res = HttpRequest.get("https://ybapi.cn/API/cy.php")
            .connectionTimeout(BaseFinal.CONNECTION_TIMEOUT)
            .timeout(BaseFinal.REQUEST_TIMEOUT)
            .header("Content-Type", "application/json")
            .send();

        if (HttpServletResponse.SC_OK != res.statusCode()) {
            System.out.println("请求失败，状态码：" + res.statusCode());
        } else {
            String responseBody = res.bodyText();
            JSONObject jsonObject = new JSONObject(responseBody);
            String data = jsonObject.get("data").toString();
            JSONObject result = new JSONObject(data);
            return result.get("song_url").toString();
        }
        return null;
    }

    /**
     * Chat-3.5模型,文字语音双重模式,无需sk,可支持连续对话 type	String	是	填 1 是返回文字,填 2 是返回语音
     *
     * @param content
     * @return
     */
    //https://ybapi.cn/API/gpt.php?type=1&msg=你好
    public static String getGpt3(String content) {
        final HttpResponse res = HttpRequest.get(
                "http://ovoa.cc/api/Bing.php?msg=" + content + "?&model=down&type=json")
            .connectionTimeout(BaseFinal.CONNECTION_TIMEOUT)
            .timeout(BaseFinal.REQUEST_TIMEOUT)
            .header("Content-Type", "application/json")
            .send();
        if (HttpServletResponse.SC_OK != res.statusCode()) {
            System.out.println("请求失败，状态码：" + res.statusCode());
        } else {
            res.charset("UTF-8");
            String responseBody = res.bodyText();
            JSONObject jsonObject = new JSONObject(responseBody);
            return jsonObject.get("content").toString();
        }
        return null;
    }

    /**
     * 全球天气
     *
     * @param content
     * @return
     */
    //http://ovoa.cc/api/tianqi.php?msg=广东&n=1
    public static String getWeather(String content, String n) {
        String url;
        if (n == null) {
            url = "http://ovoa.cc/api/tianqi.php?msg=" + content;
        } else {
            url = "http://ovoa.cc/api/tianqi.php?msg=" + content + "&n=" + n;
        }
        final HttpResponse res = HttpRequest.get(url)
            .connectionTimeout(BaseFinal.CONNECTION_TIMEOUT)
            .timeout(BaseFinal.REQUEST_TIMEOUT)
            .header("Content-Type", "application/json")
            .send();
        if (HttpServletResponse.SC_OK != res.statusCode()) {
            System.out.println("请求失败，状态码：" + res.statusCode());
        } else {
            res.charset("UTF-8");
            return res.bodyText();
        }
        return null;
    }

    /**
     * 口吐芬芳
     *
     * @return
     */
    //http://ovoa.cc/api/ktff.php
    public static String getVocal() {
        final HttpResponse res = HttpRequest.get(
                "http://ovoa.cc/api/ktff.php")
            .connectionTimeout(BaseFinal.CONNECTION_TIMEOUT)
            .timeout(BaseFinal.REQUEST_TIMEOUT)
            .header("Content-Type", "application/json")
            .send();
        if (HttpServletResponse.SC_OK != res.statusCode()) {
            System.out.println("请求失败，状态码：" + res.statusCode());
        } else {
            res.charset("UTF-8");
            return res.bodyText();
        }
        return null;
    }

    /**
     * 王者海报
     *
     * @param content
     * @return
     */
    //http://kloping.top/api/get/pvp-skin?name=瑶
    public static JSONArray getWzHaiBao(String content) {
        final HttpResponse res = HttpRequest.get(
                "http://kloping.top/api/get/pvp-skin?name=" + content)
            .connectionTimeout(BaseFinal.CONNECTION_TIMEOUT)
            .timeout(BaseFinal.REQUEST_TIMEOUT)
            .header("Content-Type", "application/json")
            .send();
        if (HttpServletResponse.SC_OK != res.statusCode()) {
            System.out.println("请求失败，状态码：" + res.statusCode());
        } else {
            res.charset("UTF-8");
            String responseBody = res.bodyText();
            return new JSONArray(responseBody);
        }
        return null;
    }

    //https://v2.api-m.com/api/random4kPic?type=acg
    //https://v.api.aa1.cn/api/api-fj-2/index.php?aa1=yuantu
    public static String getRandom4kPic() {
        String[] type = {"acg"};//, "wallpaper"
        Random random = new Random();
        int randomIndex = random.nextInt(type.length);
        String randomType = type[randomIndex];
        final HttpResponse res = HttpRequest.get(
                "https://api.52vmy.cn/api/img/pc")
            .header("Content-Type", "application/json")
            .send();
        if (HttpServletResponse.SC_OK != res.statusCode()) {
            System.out.println("请求失败，状态码：" + res.statusCode());
        } else {
            res.charset("UTF-8");
            String responseBody = res.bodyText();
            JSONObject jsonObject = new JSONObject(responseBody);
            return jsonObject.get("url").toString();
        }
        return null;
    }

    /**
     * 每日新闻快讯v5版-带天气播报
     *
     * @return
     */
    //https://www.apii.cn/api/60s-v5/?city=%E5%8C%97%E4%BA%AC
    public static String newsv5() {
        final HttpResponse res = HttpRequest.get(
                "https://www.apii.cn/api/60s-v5/")
            .header("Content-Type", "application/json")
            .send();
        if (HttpServletResponse.SC_OK != res.statusCode()) {
            System.out.println("请求失败，状态码：" + res.statusCode());
        } else {
//            res.charset("UTF-8");
            String responseBody = res.bodyText();
//            JSONObject jsonObject = new JSONObject(responseBody);
            return responseBody;
        }
        return null;
    }

    public static String getBirds() {
        final HttpResponse res = HttpRequest.get(
                "https://api.mu-jie.cc/stray-birds/range?type=json")
            .header("Content-Type", "application/json")
            .send();
        if (HttpServletResponse.SC_OK != res.statusCode()) {
            System.out.println("请求失败，状态码：" + res.statusCode());
        } else {
            res.charset("UTF-8");
            String responseBody = res.bodyText();
            JSONObject jsonObject = new JSONObject(responseBody);
            return jsonObject.get("cn").toString();
        }
        return null;
    }

    /**
     * 压缩字节数组
     *
     * @param data 要压缩的字节数组
     * @return 压缩后的字节数组
     * @throws Exception 压缩过程中可能抛出的异常
     */
    private static byte[] compress(byte[] data) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        gzip.write(data);
        gzip.close();
        byte[] compressed = bos.toByteArray();
        bos.close();
        return compressed;
    }

    /**
     * 解压缩字节数组
     *
     * @param compressedData 要解压缩的字节数组
     * @return 解压缩后的字节数组
     * @throws Exception 解压缩过程中可能抛出的异常
     */
    private static byte[] decompress(byte[] compressedData) throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(compressedData);
        GZIPInputStream gis = new GZIPInputStream(bis);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = gis.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        gis.close();
        bos.close();
        return bos.toByteArray();
    }

}
