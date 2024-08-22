## v1.0.1 (2024-08-05)

社区添加了类似 bilibili 的 Web 在线直播功能

### ✨ 新特性

WebRTC + SRS + FFmpeg

参考文档： https://ossrs.net/lts/zh-cn/docs/v5/doc/introduction

* 浏览器向srs服务器推拉流。
* 原生 webrtc 推拉流
* srs webrtc 推流，http-flv 或 hls拉流
* OBS、FFmpeg推流

### 使用FFmpeg后台推流

```shell
//查看ffempeg进程
ps -ef | grep ffempeg

//杀掉进程
kill -9 pid

//推流
nohup ffmpeg -stream_loop -1 -i 流媒体文件  -c copy -f flv 'rtmp://ip/live/livestream/roomId_xxxxxx' >> /dev/null 2>&1 &
```

## v1.0.0 (2024-07-25)

帮助运营者实现内容运营，用户运营，活动运营等需求，提供完整的社区运营功能。

### ✨ 新特性

* 社区版块：版块分类、版块用户权限（加入、访问、发帖、评论等）。
* 多类型帖子：不同类型帖子：帖子（图文）、动态（短文+多图）。
* 用户版块：可查看最近活跃、最近加入的用户，选择喜欢的用户并关注或取消关注。
* 消息通知：评论或点赞等一系列功能触发机制进行实时消息通知。
* 频道版块：支持用户自定义频道，主要对优质内容的首页推荐和聚合。
* 积分体系：“虚拟货币体系”，提升用户在平台持续活跃留存，激励用户在平台实现拉新/付费。

### other

不用在idea中设置，而是要在git中设置。
对windows用户来说，只需要在命令行执行如下命令即可。这样就可以通过代理访问github了。

如果是http代理：

```git
git config --global http.https://github.com.proxy http://127.0.0.1:7890
```

//如果是sock代理

```git
git config --global http.https://github.com.proxy socks5://127.0.0.1:1080
```

如果需要代理所有各种仓库的，则执行如下命令：
如果是http代理：

```git
git config --global http.proxy http://127.0.0.1:7890
```

如果是sock代理

```git
git config --global http.proxy socks5://127.0.0.1:1080
```


