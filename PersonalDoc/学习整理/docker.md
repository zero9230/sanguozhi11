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

![Screen Shot 2021-09-14 at 12.11.16](docker.assets/Screen%20Shot%202021-09-14%20at%2012.11.16.png)



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



# DockerFile

dockerfile就是用来构建docker镜像的构建脚本文件。命令脚本

> 方式二：







# Docker网络原理









# IDEA整合Docker





企业实战

# Docker Compose

# Docker Swarm



# CI/CD Jenkins

