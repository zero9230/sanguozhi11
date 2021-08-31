# redis

## 概述

>  redis官网
>
> [英文官网](https://redis.io/)
>
> [中文官网](http://www.redis.cn/)

redis（remote dictionary server），远程字典服务

使用ANSI，C语言编写

速度：读11W 次/s，写8W 次/s



> 作用

- 内存存储，持久化
- 效率高，高速缓存
- 发布订阅系统
- 地图信息分析
- 计时器，计数器



> 特性

1. 多样数据类型
2. 持久化
3. 集群
4. 事务



单机多redis集群

> benchmark	压测工具

压测命令：

```shell
redis-benchmark -h 127.0.0.1 -p 6379 -c 50 -n 10000
```



![img](https://images2017.cnblogs.com/blog/707331/201802/707331-20180201145503750-901697180.png)



## redis基础知识

默认16个数据库，默认使用第0个

>  切换数据库

```redis
select 3 #切换数据库
DBSIZE	# 查看数据库大小
```

> 清除数据库

```redis
flushdb	# 清除当前数据库
flushall	# 清除所有数据库
```

> redis单线程

redis很快，性能瓶颈不是CPU，而是机器内存和网络带宽

* 为什么单线程这么快？

1. 误区1: 高性能服务器一定是多线程
2. 误区2: 多线程一定比单线程效率高
3. 核心：redis将所有数据放在内存中，所以单线程效率最高。对于内存系统来说，没有上下文切换，效率就是最高的！多次读写都是在一个CPU上



## 数据类型

redis-key

```redis 
ttl KEY    # 查看KEY的剩余有效时间
move KEY 1 	# 移除当前的KEY
expire KEY N	# 在N秒后让KEY过期
```



### 基本类型

- string	字符串

```redis
setex key3 60	"hello"	# 设置key3的值为"hello"，60s后过期
setnx mykey "redis"	# 设置mykey的值为"redis"，成功（未占用）则返回1，失败则返回0；

mset k1 v1 [k2 v2 k3 v3] # 批量设置多个值
mget k1 [k2 k3]	# 批量获取多个key
mset k1 v1 [k2 v2 k3 v3] # 批量设置多个值，不覆盖，原子化操作

getset db redis # 先获得db的值并返回，然后把db的值设为redis；可用于更新、CAS的操作
```



- list

list命令都是以l开头

```redis
LPUSH list one 	# 向list中从左端添加值one
LPUSH list two
LPUSH list three

LRANGE list 0 -1	# 从左往右获取list中所有的值
LRANGE list 0 1		# 从左往右获取list中下标从0到1的值

LPOP list	# 从左端弹出一个元素
RPOP list	# 从右端弹出一个元素

LINDEX list 1 # 从左端通过下标获取元素

Llen list	# 获取list的长度

Lrem list 2 one # 从左往右移除list中值为one的元素2次

Ltrim list 1 2 # 从左端截取list中从下标从1到2的元素集合——改变list中的内容！

rpoplpush list list1 # 把list中的右端元素弹出，然后从左端将该元素压入list1中；返回该										 # 弹出元素
Lset list 1 item	# 把list中下标为1的元素赋值为item，下标不存在会报err

Linsert list before "world" "other" # 在list列表中的world前插入值"other"
Linsert list after hello new # 在list列表中hello前插入值new


```



- set

set中内容无序不重复

```redis
sadd myset hello				 # 向myset中添加值hello
smembers myset					 # 查看myset中的所有元素
sismember myset hello		 # 判断myset中是否包含hello，1/0表示真/假
srem myset hello				 # 移除myset中的hello元素
srandmember myset				 # 从myset中随机查出一个元素


```





- hash
- zset







