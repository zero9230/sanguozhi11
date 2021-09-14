# 概述

轻量级环境

隔离机制，可以讲服务器利用到极致



### docker基本组成

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

```bash
docker version	# 显示docker版本
docker info	# 显示docker系统信息，
docker 命令 --help

```





# docker镜像



# 容器数据卷



# DockerFile



# Docker网络原理



# IDEA整合Docker



# Docker Compose

# Docker Swarm



# CI/CD Jenkins

