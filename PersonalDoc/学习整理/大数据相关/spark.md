# 1 概述

一个重要的抽象模型是RDD（resilient distributed dataset，弹性分布式数据集）。



性能：在内存中比hadoop MapReduce快100倍，磁盘中快10倍

库：提供SQL库，机器学习库MLlib，图形处理GraphX和流处理Spark Streaming

![spark-stack](spark.assets/spark-stack.png)



运行：可以运行在Hadoop、Mesos、standalone和cloud，可以访问不同数据源，如：HDFS，Cassandra，HBase，S3





hadoop

- hdfs
- map-reduce

spark

- 基于内存数据操作


# 2 from share
王尧share
1. data process
场景： word count，统计文件中每个单词出现的次数


2. spark是什么
![](spark.assets/image-20220804135713335.png)


yarn集群

3. spark趋势

spark SQL 
语法树（编译原理部分）
![](spark.assets/image-20220804140650215.png)



spark推荐学习spark3.0版本



# 3 参考文献

1.   [Spark学习笔记之入门篇一](http://lousama.com/2016/01/11/Spark%E5%85%A5%E9%97%A8%E7%AF%87/) ——该文完成度很低
2.   [Spark 学习笔记](https://blog.einverne.info/post/2017/01/spark.html) 
3.   [Apache Spark 2.2.0 官方文档中文版](https://blog.csdn.net/u012185296/article/details/76855770) 
4. 🌟 [Spark教程](https://www.hadoopdoc.com/spark/spark-sparkcontext)






