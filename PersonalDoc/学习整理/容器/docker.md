# 概述

轻量级环境

隔离机制，可以讲服务器利用到极致

## docker基本组成

![Screen Shot 2021-09-13 at 11.28.52](docker.assets/Screen%20Shot%202021-09-13%20at%2011.28.52.png)

- 镜像（image）

  类似模板，通过模板来创建容器服务。tomcat镜像——》run——〉tomcat01容器，通过景象可一创建多个容器
- 容器（container）

  docker利用容器技术，地理运行一个或一组应用，通过镜像来创建

  启动、停止、删除、基本命令

  可以理解为简易Linux系统
- 仓库（repository）

  放镜像的地方

  仓库可分为共有仓库和私有仓库

  Docker Hub

# 安装

## docker为什么比虚拟机快？

1. 抽象层更少

   ![Screen Shot 2021-09-13 at 17.47.55](docker.assets/Screen%20Shot%202021-09-13%20at%2017.47.55.png)
2. 直接利用宿主机的内核，没有guest OS

   新建一个容器的时候，docker不需要像虚拟机一样重新加载一个操作系统内核，避免引导

   ![Screen Shot 2021-09-13 at 17.52.12](docker.assets/Screen%20Shot%202021-09-13%20at%2017.52.12.png)

# docker常用命令

> [docker命令官网文档](https://docs.docker.com/engine/reference/run/)

```bash
docker version	# 显示docker版本
docker info	# 显示docker系统信息，
docker 命令 --help

```

## 镜像命令

```bash
docker images	# 查看所有本地主机上的镜像
# 可选项
-a --all	# 显示所有镜像
-q --quite	# 只显示镜像id


docker search	# 搜索
docker search mysql	# 搜索mysql相关的docker镜像
# 可选项
--filter=STARS>3000	# 搜索STARS大于3000的镜像

docker pull		# 下载镜像
docker pull mysql
docker pull docker.io/library/mysql:latest	# 等价于上面的命令
docker pull mysql:5.7

docker rmi	# 删除镜像
docker rmi -f 1234567899					# 删除指定id的镜像
docker rmi -f $(docker images -aq)	# 查出本地所有镜像id，然后执行删除

```

## 容器命令

### 基本命令

```bash
# 新建容器并启动
docker run [可选参数] image

# 参数说明
--name="Name" 容器名字
-d						后台方式
-it						交互方式运行，进入容器查看内容
-p						指定容器端口，如 -p 8080:8080
	-p	ip:主机端口:容器端口
	-p	主机端口：容器端口（常用）
	-p	容器端口
-P						随机指定端口

# 测试
docker run -it centos /bin/bash	# 启动并进入容器

# 查看所有正在运行的命令
docker ps	# 列出当前正在运行的容器
-a	# 列出当前正在运行的容器	+	历史运行过的容器
-n=?	# 列出最近创建的容器
-q	# 列出当前运行容器的id

# 退出容器
exit				# 直接退出容器并退出
ctrl + P + Q	# 容器不停止退出

# 删除容器
docker rm 容器id		# 删除指定容器，不能删除正在运行的容器；如果要强制删除，用	rm -f
docker rm -f $(docker ps -aq)	# 删除所有容器
docker ps -a q|xargs rm				# 删除所有容器

# 启动和停止容器
docker start 容器id		# 启动容器
docker restart 容器id	# 重启容器
docker stop 容器id		# 停止正在运行的容器
docker kill 容器id		# 强制停止容器

```

### 其他命令：日志，元数据，进程

```bash
# 后台启动
# 命令 docker run -d 镜像名
docker run -d centos
# 常见的坑：docker容器后台运行，必须要有一个前台进程，如果没有应用就会自动停止

# 查看日志
docker logs
docker logs -tf --tail 10 1234567890
	-tf				# 显示日志
	--tail 10	# 取尾部10条

# 查看容器中进程信息
top命令
docker top 容器id

# 查看镜像的元数据
docker inspect 容器id
```

### 进入当前正在运行的容器

通常容器都是使用后台的方式运行，需要进入容器修改配置

```bash
# 方式一
docker exec -it 容器id bashShell	# 进入后台运行的容器
# 测试
docker exec -it 1234567890 /bin/bash

# 方式二
docker attach 容器id
# 测试
docker attafch 1234567890

# docker exec				# 进入容器后开启一个新终端，可以在里面操作（常用）
# docker attach			# 进入容器正在执行的终端，不会启动新进程
```

### 容器间内容拷贝

```bash
# 将容器里的文件拷贝出来到主机上
docker cp 容器id:容器内路径	目的地主机路径
# 测试
docker cp 1234567890:/home/test.java /home

# 拷贝是手动过程， 后续使用-v 容器卷技术进行批量拷贝
```

### 命令小结

![Screen Shot 2021-09-15 at 10.29.52](docker.assets/Screen%20Shot%202021-09-15%20at%2010.29.52.png)

### **作业练习

> docker安装nginx

```bash
1. 搜索镜像
2. 下载镜像
3. 运行测试

```

> Docker装tomcat

> 部署es+kibana

## 可视化

- portainer（先用这个）

图形化管理界面

```bash
docker run -d -p 8088:9000 portainer/portainer
```

```bash
docker run -d -p 8088:9000 \
--restart=always -v /var/run/docker.sock:/var/run/docker.sock --privileged=true \
portainer/portainer
```

- Rancher（CI/CD再用）

# docker镜像

像是一种轻量级、可执行的独立软件包，包括代码、运行时、库、环境变量、配置文件

所有应用，直接打包docker镜像

> UnionFS：联合文件系统

分层管理的文件系统

bootFS：系统加载引导，主要包含bootloader和kernel

rootFS： 各种不同操作系统发行版，如Ubuntu，CentOS等。主要包含/dev，/proc，/bin，/etc

所有镜像起始于基础镜像

docker镜像都是只读的，容器启动时，一个新的可写层被加载到镜像顶部

commit镜像

```bash
docker commit 提交容器成为一个新副本

docker commit -a="kuangshen" -m="add webapps" 1234567890 tomcat02:1.0
```

# 容器数据卷

## 什么是容器数据卷

数据如果都在容器中，那么删除容器会导致数据丢失。因此需要对数据持久化和同步操作

容器之间进行数据共享的技术。docker容器中产生的数据，同步到本地

核心是**目录挂载**

## 使用数据卷

> 方式一：直接使用命令挂载 -v

```bash
docker run -it -v 主机目录：容器内目录
# 启动后，可以通过 docker inspect 容器id命令查看挂在情况，在Mounts中

docker run mysql -d -p 3310:3306 -e MYSQL_ROOT_PASSWORD=123456 -v /home/mysql/conf:/etc/mysql/conf.d -v /home/mysql/data:/var/lib/mysql
```

即使容器删除后，挂载在本地的数据卷依旧没有删除

## 具名和匿名挂载

```bash
# 匿名挂载
-v 容器内路径
docker run -d -P --name nginx01 -v /etc/nginx nginx

# 查看所有容器卷
docker  volume ls

# -v  只写了容器内路径，没写容器外路径，因此称为匿名挂载

# 具名挂载
docker run -d -P --name nginx02 -v juming-nginx:/etc/nginx nginx

# 通过 - v 卷名：容器内路径
```

所有docker容器内的卷，没指定目录的情况下默认在  `/var/lib/docker/volumes/xxx/_data` 路径下

通过具名挂载可以方便找到一个卷，因此常用

> 如何确定是具名挂载还是匿名挂载？还是指定路径挂载

```bash
-v 容器内路径							# 匿名
-v 卷名:容器内路径					# 具名
-v /宿主机路径::容器内路径	# 指定路径挂载
```

扩展

```bash
# 通过-v容器内路径：ro  rw改变读写权限
	ro	readonly	# 只读
	rw	readwrite	# 可读可写
```

## 数据卷之DockerFile

dockerfile就是用来构建docker镜像的构建脚本文件。命令脚本

> 方式二：

```bash
# 创建一个dockerfile文件，名字建议Dockerfile

# 文件内容 指令（大写）参数
FROM CENTOS
VOLUME ["volume01","volume02"]
CMD echo "----end---"
CMD /bin/bash

# 构建自定义镜像
docker build -f dockerfile1 -t kuangshen/centos:1.0
```

假设构建的时候没有挂载卷，要手动镜像挂载， -v 卷名：容器内路径

## 数据卷容器

多个mysql数据同步！

```shell
# 启动3个容器

# 启动同步容器 
# --volumes-from 容器名 
# 测试
docker run -it --name docker02 --volumes-from docker01 kuangshen/centos:1.0

# 删除了docker01，docker02和docker03还是可以访问该文件
```

拷贝的概念

![截屏2021-09-14 22.14.13](docker.assets/%E6%88%AA%E5%B1%8F2021-09-14%2022.14.13.png)

多个mysql实现数据共享

结论：

容器之间配置信息的传递，数据卷容器的生命周期一直持续到没有容器使用为止

但是一旦持久化到了本地，本地的数据是不会删除的

# Dockerfile

构建步骤

1. 编写一个dockerfile文件
2. docker build构建为一个镜像
3. docker run运行镜像
4. docker push 发布镜像（DockerHub等）

## Dockerfile构建过程

**基础知识：**

1. 每个保留关键字必须是大写字母
2. 执行从上到下顺序执行
3. “#” 表示注释
4. 每个指令都会创建提交一个新的镜像层，并提交

![截屏2021-09-14 22.27.54](docker.assets/%E6%88%AA%E5%B1%8F2021-09-14%2022.27.54-1629708.png)

dockerfile：构建文件，定义了一切步骤，源代码

docker镜像：通过dockerfile构建生成的镜像，最终发布和运行的产品

docker容器：容器是镜像运行起来的实例，可提供服务

## Dockerfile指令

```bash
FROM				# 基础镜像，从这里开始构建
MAINTAINER	# 镜像是谁写的，姓名+邮箱
RUN					# 镜像构建的时候需要运行的命令
ADD					# 步骤：tomcat镜像，这个tomcat压缩包！添加内容
WORKDIR			# 镜像的工作目录
VOLUME			# 挂载的目录
EXPOSE			# 暴露端口号配置

CMD					# 指定这个容器启动的时候要运行的命令，只有最后一个会生效，可被替代
ENTRYPOINT	# 指定这个容器启动的时候要运行的命令，可以追加命令
ONBUILD			# 当构建一个被继承Dockerfile，这个时候就会运行ONBUILD命令，触发指令
COPY				# 类似ADD命令，将文件拷贝到镜像中
ENV					# 构建的时候设置环境变量，可用于设置用户名密码等
```

## 实战测试

DockerHub中99%的镜像都是从 `FROM scratch` 开始构建

1. 编写dockerfile文件

```shell
FROM centos
MAINTAINER zero9230<mengnianyang9230@163.com>

ENV MYPATH /usr/local
WORKDIR ${MYPATH}

RUN yum -y install vim
RUN yum -y install net-tools

EXPOSE 80

CMD echo ${MYPATH}
CMD echo "---end---"
CMD /bin/bash

```

2. 构建

   ```shell
   # 命令 docker build -f dockerfile文件路径 -t 镜像名:[tag] .
   docker build -f mydockerfile-centos -t mycentos .
   
   # docker history 容器id	# 查看容器变更历史
   docker history 容器id
   ```

> CMD和ENTRYPOINT的区别

```bash
CMD					# 指定这个容器启动的时候要运行的命令，只有最后一个会生效，可被替代
ENTRYPOINT	# 指定这个容器启动的时候要运行的命令，可以追加命令
```

## 实战tomcat镜像

1. 准备tomcat、jdk安装包
2. 官方命名 `Dockerfile` ，否则需要使用 `-f` 指定文件
3. 构建镜像

   ```bash
   docker build -f Dockerfile .
   ```
4. 启动镜像
5. 访问测试
6. 发布项目

## 小结

![Screen Shot 2021-09-15 at 10.24.20](docker.assets/Screen%20Shot%202021-09-15%20at%2010.24.20.png)

# Docker网络

## 理解Docker0

三个网络

```bash
# 问题：docker如何处理容器网络访问？
# linux可以ping通docker容器内部
```

> 原理

1. 每启动一个docker容器，docker就会给docker容器分配一个ip，只要安装了docker，就会有一个网卡docker0桥接模式，使用evth-pair技术
2. 再启动一个tomcat，发现多了一对网卡

```
容器带来的网卡都是成堆的
evth-pair就是一对虚拟设备接口，成对出现，一端连着协议，一端彼此连接
充当一个桥梁，连接各种虚拟设备
OpenStac，Docker容器之间的连接，OVS的连接，都是使用evth-pair技术
```

3. 结论：容器和容器之间是可以互相ping通的

![Screen Shot 2021-09-15 at 11.05.31](docker.assets/Screen%20Shot%202021-09-15%20at%2011.05.31.png)

docker使用桥接，宿主机中是一个docker容器的网桥——docker0

![Screen Shot 2021-09-15 at 11.10.20](docker.assets/Screen%20Shot%202021-09-15%20at%2011.10.20.png)

Docker中所有网络接口都是虚拟的，，虚拟的转发效率高！（内网传递文件）

只要容器删除，对应网桥一对就没了

探究：inspect

```bash
--link 就是我们在hosts配置中增加了一个172.18.0.3 tomcat02 123456789
现在使用Docker已经不建议使用--link了
```

自定义网络不适用docker0

问题：不支持容器名连接访问

## 自定义网络

容器互联

#### 网络模式

bridge：桥接模式（默认）

none：不配置网络

host：和宿主机共享网络

container： 容器网络连通（用得少，局限很大）

直接启动的命令，会默认带上 `--net bridge`

```bash
docker network ls	# 查看所有网络

# 自定义网络
--driver bridge
--subnet 192.168.0.0/16
--gateway 192.168.0.1

```

## 网络连通

```bash
docker network connect mynet tomcat01
# 连通之后就是将 tomcat01 放到 mynet网络下
# 一个容器两个ip地址
```

# 实战部署redis集群

# IDEA整合Docker

企业实战

# Docker Compose

# Docker Swarm

# CI/CD Jenkins
