# 1 spring注解

## 1.1 `@Inject` 注解

可用于三种类成员，表示该成员需要注入依赖项

1. 构造方法
2. 方法
3. 属性

作用于属性时，

用法基本和 `@Autowired` 相同，区别在于inject没有属性，无法使用类似`@Autowired(required=false)` 的属性



## 1.2 `@Configuration` 注解

用于定义配置类，可替换xml配置文件，被注解的类中含有若干个@bean注解的方法，这些方法会被AnnotationConfigApplicationContext或AnnotationConfigWebApplicationContext类进行扫描，用于构建bean定义，初始化Spring容器
