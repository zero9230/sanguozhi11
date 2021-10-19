# oracle快照机制

oracle生成快照需要导出为文件，有一定的系统开销





# oracle事务

一样也分为四种隔离级别，和mysql一样



使用可重复读的事务隔离？



## 可重复读的实现



参考

- https://www.zhihu.com/question/434734816/answer/1625842017
-  [高性能MySQL-如何实现可重复读？](https://zhuanlan.zhihu.com/p/161933980) 



# 基础语法

## 查询语句顺序

```sql
SELECT
    col_a /(*) /(聚合函数)
FROM
    tb
WHERE
    条件
GROUP BY
    col_a
HAVING
    COUNT(*) > 5
ORDER BY
    sum DESC
LIMIT
    10;
```



# oracle分库分表



# DB连接

目前GG表采用的JDBC直连

其他表会采用_compdb   连接池的方式连接



```sql
SELECT
    GG_CM_CASE_ENTITY_INFO.GG_COMMIT_TIMESTAMP,
    TO_NUMBER(
        CONCAT(
            GG_CM_CASE_ENTITY_INFO.GG_TRAIL_SEQ,
            LPAD(GG_CM_CASE_ENTITY_INFO.GG_TRAIL_RBA, 10, 0)
        )
    ) AS GG_RBA,
    GG_CM_CASE_ENTITY_INFO.CASE_TID,
    GG_CM_CASE_ENTITY_INFO.ENTITY_TYPE,
    GG_CM_CASE_ENTITY_INFO.ENTITY_ID,
    GG_CM_CASE_ENTITY_INFO.ENTITY_ROLE,
    GG_CM_CASE_ENTITY_INFO.ADJACENCY_ID,
    GG_CM_CASE_ENTITY_INFO.LINK_REASON,
    GG_CM_CASE_ENTITY_INFO.CLASSIFICATION,
    GG_CM_CASE_ENTITY_INFO.TIME_CREATED,
    GG_CM_CASE_ENTITY_INFO.TIME_UPDATED
FROM
    GG_CM_CASE_ENTITY_INFO
WHERE
    GG_CM_CASE_ENTITY_INFO.ENTITY_TYPE = 'ACCOUNT'
    AND GG_CM_CASE_ENTITY_INFO.GG_OP_TYPE <> 'DELETE'
    AND GG_CM_CASE_ENTITY_INFO.GG_BEFORE_AFTER = 'AFTER'
    AND GG_CM_CASE_ENTITY_INFO.GG_COMMIT_TIMESTAMP >= TO_DATE(
        '$$MAX_TIME_gg_cm_case_entity_gi',
        'mm/dd/yyyy hh24:mi:ss'
    ) - 1 / 96
    AND GG_CM_CASE_ENTITY_INFO.GG_COMMIT_TIMESTAMP <= TO_DATE('$$PARAM_UPPER_TIME', 'yyyymmddhh24miss')
```





# 常用函数

## 数学计算类

### mod

【功能】返回x除以y的余数

【语法】

```sql
mod(number , number)
```



【参数】x,y，数字型表达式

【返回】数字

 

【示例】

```sql
 select mod(23,8),mod(24,8) from dual;
```

返回：7,0

### LPAD

【功能】左填充

lpad函数是[Oracle](https://baike.baidu.com/item/Oracle)[数据库函数](https://baike.baidu.com/item/数据库函数)，lpad函数从左边对[字符](https://baike.baidu.com/item/字符)串使用指定的字符进行填充。从其字面意思也可以理解，l是left的简写，pad是填充的意思，所以lpad就是从左边填充的意思。

【语法】

```
lpad( string, padded_length, [ pad_string ] )
```

**string**

准备被填充的字符串；

**padded_length**

填充之后的字符串长度，也就是该函数返回的字符串长度，如果这个数量比原字符串的长度要短，lpad函数将会把字符串截取成从左到右的n个字符;

**pad_string**

填充字符串，是个可选参数，这个字符串是要粘贴到string的左边，如果这个参数未写，lpad函数将会在string的左边粘贴空格。 

【示例】

```sql
LPAD(12345, 10, 0)
```

返回

```sql
0000012345
```



