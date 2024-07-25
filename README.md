## 项目打包

在 `demoNewAdmin` 根目录下打开终端，输入下方命令进行后端打包。

```bash
mvn clean package
```

等待打包完成，`demo-service/target/app` 目录下的内容即为后端项目部署物料

将 app 目录下的所有内容复制到 demo-service/docker/demo-admin 目录下。
## 部署运行

将部署目录 `demoNewAdmin/docker` （包含 docker 目录）上传到部署服务器 `/` 根目录。

```shell
# 授权
chmod -R 777 /docker

# 进入 docker-compose.yml 脚本所在目录
cd /docker

# 创建并后台运行所有容器
docker-compose up -d
```

等待自动下载镜像、构建及运行容器完成后，可通过 Docker 相关命令查看容器状态。

```shell
# 查看镜像列表
docker images

# 查看容器列表
docker ps -a

# 查看指定容器运行日志
docker logs -f 容器名称
```

目录结构应该如下：

```
/docker
 ├─ demo-admin（demoNewAdmin 部署目录及后端容器挂载目录）
 │  ├─ bin（核心程序）
 │  ├─ config（配置文件）
 │  ├─ lib（依赖 jar）
 │  ├─ html（前端部署物料）
 │  ├─ data（本地文件数据存储，自动生成）
 │  ├─ logs（日志，自动生成）
 │  └─ Dockerfile
 └─ 其他略...
```

