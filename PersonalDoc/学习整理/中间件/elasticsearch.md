# 简介

## 前言——Lucene

> Lucene简介

- Doug Cutting开发
- apache软件基金会4jakarta项目组的子项目
- 开放源码的全文检索引擎工具包
- 只是全文检索引擎的架构
- 免费java信息检索程序库

> Lucene和ElasticSearch的关系

ES是基于Lucene做了一个封装和增强



## ElasticSearch概述

ElasticSearch是一个开源的高扩展分布式全文检索引擎，可以近乎实时的存储、检索数据。使用Java开发，以Lucene为核心，目的是通过RESTFul API隐藏Lucene的复杂性

> 安装要求
>
> JDK8，最低要求







建立在全文搜索引擎库 Apache Lucene上。elasticsearch使用java编写，隐藏Lucene的复杂性，提供一套简单一致的RESTful API

特征：

1. 分布式实时文档存储，每个字段可被索引和搜索
2. 实时分布式
3. 上百个服务节点扩展，支持PB级别结构化和非结构化数据



启动elasticsearch

1. 切换到ES的目录下
2. 执行bin/elasticsearch

```shell
cd elasticsearch-<version>
./bin/elasticsearch  
```



测试ES是否启动成功

```shell
curl 'http://localhost:9200/?pretty'
```





# 参考文献

1.  [ElasticSearch7.6入门学习笔记](https://www.kuangstudy.com/bbs/1354069127022583809) 
2.  [【狂神说Java】ElasticSearch7.6.x最新完整教程通俗易懂](https://www.bilibili.com/video/BV17a4y1x7zq?from=search&seid=4820966176885181951&spm_id_from=333.337.0.0) 
3.  [elasticsearch官方文档地址](https://www.elastic.co/guide/cn/elasticsearch/guide/current/running-elasticsearch.html) 
3.  [ES下载地址](https://www.elastic.co/cn/downloads/)
