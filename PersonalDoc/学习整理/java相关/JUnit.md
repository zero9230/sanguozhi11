# 1 测试

```java
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FactorialTest {

    @Test
    void testFact() {
        assertEquals(1, Factorial.fact(1));
        assertEquals(2, Factorial.fact(2));
        assertEquals(6, Factorial.fact(3));
        assertEquals(3628800, Factorial.fact(10));
        assertEquals(2432902008176640000L, Factorial.fact(20));
    }
}
```

核心测试方法`testFact()`加上了`@Test`注解，这是JUnit要求的，它会把带有`@Test`的方法识别为测试方法。



 `assertEquals(expected, actual)` 









# 2 参考文献

1. [IDEA中Junit单元测试的使用(初级篇)](https://blog.csdn.net/qq_36568192/article/details/79857185) 
1.  [廖雪峰-编写JUnit测试](https://www.liaoxuefeng.com/wiki/1252599548343744/1304048154181666) 
1.   [JUnit 5 tutorial - Learn how to write unit tests](https://www.vogella.com/tutorials/JUnit/article.html#junitsetup) 