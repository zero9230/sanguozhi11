# 知识体系



核心：自动注解

几个自动注解的类

其中在application.yaml或application.properties中的配置信息，给出提示的都是可以找到对应配置类的

不同配置会有不同的导入条件





# 常用注解

## Value注解

### 使用Value注解注入集合类

我今天要讲的配置注入时基于*.properties文件的，yaml格式可能有点区别。借助@Value注解、SPEL表达式实现，请看以下示例：

- List

```java
    @Value("#{'${list.type}'.split(',')}")
    private List<String> listType;
```

```java
	list.type=1,2,3,4
```



- Set

```java
    @Value("#{'${spring.redis.cluster.nodes}'.split(',')}")
    private Set<String> clusterHostAndPorts;
```

```java
spring.redis.cluster.nodes=10.199.153.166:7001,10.199.153.166:7002
```



- Map

```java
    @Value("#{${map.config}}")
    private ConcurrentHashMap<String,String> map;
```

```java
	map.config={'01':"AA",'02':"BB",'03':"CC",'04':"DD"}
```


注意点：
测试的时候发现，如果key值不加引号，如果配置的key为01，而map中得到的key为1，少了个0，所以将key值加上了引号
