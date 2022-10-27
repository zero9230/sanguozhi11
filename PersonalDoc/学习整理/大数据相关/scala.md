

# 1 特点

1. oop语言，每个操作符都是对象；函数式语言，可以将函数作为参数传递。
2. scala代码通过`scalac`编译成`.class`文件，



# 2 基础语法
## 2.1 变量声明

```scala
//声明变量实例如下：
var myVar : String = "Foo"
var myVar : String = "Too"
```
以上定义了变量 myVar，我们可以修改它。

```scala
//声明常量实例如下：
val myVal : String = "Foo"
```
以上定义了常量 myVal，它是不能修改的。如果程序尝试修改常量 myVal 的值，程序将会在编译时报错。


```scala
//字符串操作。
//scala中的String类就是java中的String类

```


## 2.2 数据类型
```scala
//数据类型

//Byte Short Int Long Char String Float Double Boolean
//除了String外，其他都在scala包下
//以上九个类在scala中有富包装类提供额外方法
//scala.runtime.RichByte等等
```



```scala
//操作符
//操作符 即 方法
```

## 2.3 方法定义

方法定义由一个 **def** 关键字开始，紧接着是可选的参数列表，一个冒号 : 和方法的返回类型，一个等于号 = ，最后是方法的主体。

Scala 方法定义格式如下：
```scala
def functionName ([参数列表]) : [return type] = {
   function body
   return [expr]
}
```
如
```scala
def sayHello( ) :Unit = {
	println("Hello")
}
```

```scala
object add{  
   def addInt( a:Int, b:Int ) : Int = {  
      var sum:Int = 0  
      sum = a + b  
      return sum  
   }  
}
```

# 3 参考资料
1. [runoob-Scala 教程](https://www.runoob.com/scala/scala-tutorial.html) 
2. 


