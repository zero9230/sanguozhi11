# spring注解

 `@Inject` 注解

可用于三种类成员，表示该成员需要注入依赖项

1. 构造方法
2. 方法
3. 属性

作用于属性时，

用法基本和 `@Autowired` 相同，区别在于inject没有属性，无法使用类似`@Autowired(required=false)` 的属性

