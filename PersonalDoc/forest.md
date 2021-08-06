# 简介

forest数据包，包括索引文件，存储文件，字段表

* 如何根据类目id获取类目对象？

forest客户端启动时，加载索引文件，将所有类目反序列化为对象放在数组里。该数据按类目ID排序。查找时采用二分查找



- 索引文件index

parent与children；

name、memo、features；索引中只存nameId、memoId、featureId，用于查字典表

catType、channelId、sortOrder、commodityId：索引中没有，只存一个recordID，用于在存储文件中查询



- 存储文件store

1. 存储文件同步MappedByteBuffer映射到堆外内存（零拷贝？）

record ( catId , catType , channelId , sortOrder , commodityId )

2. 数据记录都是定长数据，因此可以用下述方法获得文件位置

```text
position= metaHeadSize + recordId * recordSize
```

根据position 和recordSize可从整个mappedByteBuffer中截取只包含某个类目信息的subBuffer



- 字段表文件



# 加载

如何手动清理内存区？



# 微包化

























