# 1 HDFS
HDFS: hadoop  distributed file system（hadoop分布式文件系统）

Hadoop有1，2，3三个版本

每个block体积为64M，文件超过单个block的，会使用多个block合起来存储

- NameNode： 存储Hadoop的元数据，文件和block，block和dataNode

- dataNode： 存放实际数据，3个备份

HDFS为主从架构，


## 1.1 常见命令
1. 查看所有文件
```bash
hdfs dfs -ls
```



# 2 Hive

# 3 Hadoop
建议从2开始学，不建议学3
