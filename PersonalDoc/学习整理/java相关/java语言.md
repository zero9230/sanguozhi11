# java基础语法

## continue

continue带上参数，即为跳出当次循环，到指定标记位执行下一次循环。一般用于多重循环中

```java
while(!list.empty()){
  FLAG;
  for(int i:list2){
    if(i==0){
      continue FLAG;
    }
  }
}
```





# java8新特性

补充下java8的特性，着重关注stream API的使用，有的东西不太记得了

## lambda表达式

语法形式：

```java
(params)-> expression
(params)->{statements;}
```



[菜鸟教程-Java Lambda 表达式](https://www.runoob.com/java/java8-lambda-expressions.html)

## 方法引用

方法引用使用一对冒号 `::` 



[Java 8 方法引用](https://www.runoob.com/java/java8-method-references.html)

## 默认方法

## 新工具

## stream API

## Date Time API

## Optional类

## Nashorn Javascript引擎



# 参考文献

1.  [菜鸟教程-Java 8 新特性](https://www.runoob.com/java/java8-new-features.html) 

