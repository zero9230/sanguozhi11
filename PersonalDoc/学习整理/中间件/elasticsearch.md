

[elasticsearch官方文档地址](https://www.elastic.co/guide/cn/elasticsearch/guide/current/running-elasticsearch.html)

# 简介

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



# 文档

