# 1 JUnit4

由于JUnit5需要java11及以上版本，因此我们选用JUnit4

在github上开源，地址： [junit4](https://github.com/junit-team/junit4) 

使用断言语句检查预期结果

## 1.1 JUnit框架

测试框架，使用注释指定测试的方法，

## 1.2 依赖

```xml
<dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>4.13</version>
  <scope>test</scope>
</dependency>
```

## 1.3 命名约定

在测试类名称的末尾使用“Test”后缀

## 1.4 用法

### 1.4.1 定义测试方法

所有这些注解都可以用在方法上。



常用注释

| JUnit 4                                | 描述                                                         |
| :------------------------------------- | :----------------------------------------------------------- |
| `import org.junit.*`                   | 使用以下注释的导入语句。                                     |
| `@Test`                                | 将方法标识为测试方法。                                       |
| `@Before`                              | 在每次测试之前执行。它用于准备测试环境（例如，读取输入数据，初始化类）。 |
| `@After`                               | 每次测试后执行。它用于清理测试环境（例如，删除临时数据、恢复默认值）。它还可以通过清理昂贵的内存结构来节省内存。 |
| `@BeforeClass`                         | 在所有测试开始之前执行一次。它用于执行时间密集型活动，例如连接到数据库。使用此注释标记的方法需要定义为`static`与 JUnit 一起使用。 |
| `@AfterClass`                          | 在所有测试完成后执行一次。它用于执行清理活动，例如，断开与数据库的连接。使用此注释注释的方法需要定义为`static`与 JUnit 一起使用。 |
| `@Ignore`或者`@Ignore("Why disabled")` | 标记应禁用测试。当底层代码已更改且测试用例尚未调整时，这很有用。或者如果此测试的执行时间太长而无法包含在内。最佳做法是提供可选描述，即禁用测试的原因。 |
| `@Test (expected = Exception.class)`   | 如果方法没有抛出指定的异常则失败。                           |
| `@Test(timeout=100)`                   | 如果该方法花费的时间超过 100 毫秒，则失败。                  |



### 1.4.2 断言语句

*断言方法*将测试返回的实际值与预期值进行比较。如果比较失败，它会抛出一个 `AssertionException` 

| 陈述                                                  | 描述                                                         |
| ----------------------------------------------------- | ------------------------------------------------------------ |
| fail([message])                                       | 让方法失败。可用于检查代码的某个部分是否未到达或在测试代码实施之前进行失败的测试。消息参数是可选的。 |
| assertTrue([message,] boolean  condition)             | 检查布尔条件是否为真。                                       |
| assertFalse([message,] boolean  condition)            | 检查布尔条件是否为假。                                       |
| assertEquals([message,]  expected, actual)            | 测试两个值是否相同。注意：对于数组，检查的是引用而不是数组的内容。 |
| assertEquals([message,]  expected, actual, tolerance) | 测试 float 或 double  值是否匹配。容差是必须相同的小数位数。 |
| assertNull([message,] object)                         | 检查对象是否为空。                                           |
| assertNotNull([message,]  object)                     | 检查对象是否不为空。                                         |
| assertSame([message,]  expected, actual)              | 检查两个变量是否引用同一个对象。                             |
| assertNotSame([message,]  expected, actual)           | 检查两个变量是否引用不同的对象。                             |



### 1.4.3 JUnit测试套件

可组合多个测试类，得到一个测试套件。按照指定顺序执行所有测试类，可嵌套。例如

```java
@RunWith(Suite.class)
@SuiteClasses({
        MyClassTest.class,
        MySecondClassTest.class })

public class AllTests {

}
```



### 1.4.4 禁用测试

`@Ignore` 注释允许静态忽略测试。或者，您可以使用`Assume.assumeFalse`或`Assume.assumeTrue`定义测试条件。 `Assume.assumeFalse`如果其条件评估为真，则将测试标记为无效。 `Assume.assumeTrue`如果条件评估为假，则将测试评估为无效。例如，以下禁用 Linux 上的测试：

```
Assume.assumeFalse(System.getProperty("os.name").contains("Linux"));
```



### 1.4.5 参数化测试

JUnit 允许您在测试类中使用参数。此类可以包含**一个**测试方法，并且该方法使用提供的不同参数执行。

使用`@RunWith(Parameterized.class)` 将测试类标记为参数化测试。

这样的测试类必须包含一个带有`@Parameters`注解的静态方法。该方法生成并返回一组数组。此集合中的每个项目都用作测试方法的参数。

使用`@Parameter` 在public字段上的注释来获取在测试中注入的测试值。

例程

```java
package testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class ParameterizedTestFields {
    // fields used together with @Parameter must be public
    @Parameter(0)
    public int m1;
    @Parameter(1)
    public int m2;
    @Parameter(2)
    public int result;

    // creates the test data
    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { 1 , 2, 2 }, { 5, 3, 15 }, { 121, 4, 484 } };
        return Arrays.asList(data);
    }

    @Test
    public void testMultiplyException() {
        MyClass tester = new MyClass();
        assertEquals("Result", result, tester.multiply(m1, m2));
    }

    // class to be tested
    class MyClass {
        public int multiply(int i, int j) {
            return i *j;
        }
    }
}
```





除了使用`@Parameter`注释之外，您还可以使用构造函数来存储每个测试的值。用注解的方法提供的每个数组中的元素个数 `@Parameters` 必须与类的构造函数中的参数个数相对应。为每个参数创建类，测试值通过构造函数传递给类。

```
package de.vogella.junit.first;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterizedTestUsingConstructor {

    private int m1;
    private int m2;

    public ParameterizedTestUsingConstructor(int p1, int p2) {
        m1 = p1;
        m2 = p2;
    }

    // creates the test data
    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { { 1 , 2 }, { 5, 3 }, { 121, 4 } };
        return Arrays.asList(data);
    }


    @Test
    public void testMultiplyException() {
        MyClass tester = new MyClass();
        assertEquals("Result", m1 * m2, tester.multiply(m1, m2));
    }


    // class to be tested
    class MyClass {
        public int multiply(int i, int j) {
            return i *j;
        }
    }

}
```

如果您运行此测试类，则会使用每个定义的参数执行测试方法。在上面的例子中，测试方法被执行了 3 次。



### 1.4.6 JUnit规则

关键字： `@Rule` 

例程：指定在执行测试期间预期的异常消息

```
package de.vogella.junit.first;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RuleExceptionTesterExample {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test
  public void throwsIllegalArgumentExceptionIfIconIsNull() {
    exception.expect(IllegalArgumentException.class);
    exception.expectMessage("Negative value not allowed");
    ClassToBeTested t = new ClassToBeTested();
    t.methodToBeTest(-1);
  }
}
```

JUnit 已经提供了几个有用的规则实现。例如，`TemporaryFolder`该类允许设置在每次测试运行后自动删除的文件和文件夹。

以下代码显示了`TemporaryFolder`实现使用的示例。

```
package de.vogella.junit.first;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class RuleTester {

  @Rule
  public TemporaryFolder folder = new TemporaryFolder();

  @Test
  public void testUsingTempFolder() throws IOException {
    File createdFolder = folder.newFolder("newfolder");
    File createdFile = folder.newFile("myfilefile.txt");
    assertTrue(createdFile.exists());
  }
}
```

有关现有规则的更多示例，请参阅https://github.com/junit-team/junit4/wiki/Rules。



### 1.4.7 自定义JUnit规则

需要实现`TestRule`接口。这个接口定义了一个方法：`apply(Statement, Description)`，该方法必须返回一个`Statement` 的实例。Statement 表示 JUnit 运行时中的测试，Statement#evaluate() 运行这些测试。描述描述了单个测试。它允许通过反射读取有关测试的信息。

以下是在测试执行前后向 Android 应用程序添加日志语句的简单示例。

```java
package testing.android.vogella.com.asynctask;

import android.util.Log;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class MyCustomRule implements TestRule {
    private Statement base;
    private Description description;

    @Override
    public Statement apply(Statement base, Description description) {
        this.base = base;
        this.description = description;
        return new MyStatement(base);
    }

    public class MyStatement extends Statement {
        private final Statement base;

        public MyStatement(Statement base) {
            this.base = base;
        }

        @Override
        public void evaluate() throws Throwable {
            System.
            Log.w("MyCustomRule",description.getMethodName() + "Started" );
            try {
                base.evaluate();
            } finally {
                Log.w("MyCustomRule",description.getMethodName() + "Finished");
            }
        }
    }
}
```

要使用此规则，只需将带有注释的字段添加`@Rule`到您的测试类。

```java
@Rule
public MyCustomRule myRule = new MyCustomRule();
```



### 1.4.8 类别Categories

可以定义测试类别并根据注释包括或排除它们。以下示例基于[JUnit 4.8 发行说明](https://github.com/junit-team/junit/blob/master/doc/ReleaseNotes4.8.md)。

```
public interface FastTests { /* category marker */
}

public interface SlowTests { /* category marker */
}

public class A {
    @Test
    public void a() {
        fail();
    }

    @Category(SlowTests.class)
    @Test
    public void b() {
    }
}

@Category({ SlowTests.class, FastTests.class })
public class B {
    @Test
    public void c() {
    }
}

@RunWith(Categories.class)
@IncludeCategory(SlowTests.class)
@SuiteClasses({ A.class, B.class })
// Note that Categories is a kind of Suite
public class SlowTestSuite {
    // Will run A.b and B.c, but not A.a
}

@RunWith(Categories.class)
@IncludeCategory(SlowTests.class)
@ExcludeCategory(FastTests.class)
@SuiteClasses({ A.class, B.class })
// Note that Categories is a kind of Suite
public class SlowTestSuite {
    // Will run A.b, but not A.a or B.c
}
```





# 2 SpringBootTest

## 2.1 依赖

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-test</artifactId>
  <scope>test</scope>
</dependency>
```



一旦依赖了spring-boot-starter-test，下面这些类库将被一同依赖进去：

- JUnit：java测试事实上的标准，默认依赖版本是4.12（JUnit5和JUnit4差别比较大，集成方式有不同）。
- Spring Test & Spring Boot Test：Spring的测试支持。
- AssertJ：提供了流式的断言方式。
- Hamcrest：提供了丰富的matcher。
- Mockito：mock框架，可以按类型创建mock对象，可以根据方法参数指定特定的响应，也支持对于mock调用过程的断言。
- JSONassert：为JSON提供了断言功能。
- JsonPath：为JSON提供了XPATH功能。

## 2.2 注解详解

按功能分类，springboot test注解分为以下几类

1. 配置类： @TestConfiguration
2. mock类： @MockBean
3. 启动测试类： @SpringBootTest
4. 自动配置类： @AutoConfigureJdbc

### 2.2.1 配置类型的注解

1. @TestComponent： 是另一种@Component，语义上用于指定某个bean专门用于测试。适用于测试代码和正视代码混合在一起时，不加在被改注解修饰的bean，使用不多
2. @TestConfiguration： 另一种@TestComponent，用于补充额外的Bean或覆盖已存在的Bean。在不修改正式代码前提下，使配置更加灵活
3. @TypeExcludeFilters： 



## 2.3 参考链接

1.  [SpringBoot Test及注解详解](https://www.cnblogs.com/myitnews/p/12330297.html) 



# 3 Mockito

模拟对象，用于测试

*Mockito* 是一个流行 mock 框架，可以和 JUnit 结合起来使用。

一般使用 Mockito 需要执行下面三步

1. 模拟并替换测试代码中外部依赖
2. 执行测试代码
3. 验证测试代码是否被正确的执行



## 3.1 引入依赖

Maven repo中 搜索 g:"org.mockito", a:"mockito-core"

```xml
<dependency>
	<groupId>org.mockito</groupId>
  <artifactId>mockito-core</artifactId>
  <version>4.3.0</version>
</dependency>
```

有时也需要替换成mockito-linit的依赖

## 3.2 配置mock

`when(….).thenReturn(….)`可以被用来定义当条件满足时函数的返回值，如果你需要定义多个返回值，可以多次定义。当你多次调用函数的时候，Mockito 会根据你定义的先后顺序来返回返回值。该行为一般也称为打桩

对于无返回值的函数，我们可以使用`doReturn(…).when(…).methodCall`来获得类似的效果。



## 3.3 验证mock对象方法是否被调用

Mockito会追踪mock对象里所有方法和变量。

主要方法为：`verify`，例程如下：

```java
import static org.mockito.Mockito.*;

@Test
public void testVerify()  {
        // 创建并配置 mock 对象
        MyClass test = Mockito.mock(MyClass.class);
        when(test.getUniqueId()).thenReturn(43);

        // 调用mock对象里面的方法并传入参数为12
        test.testing(12);
        test.getUniqueId();
        test.getUniqueId();

        // 查看在传入参数为12的时候方法是否被调用
        verify(test).testing(Matchers.eq(12));

        // 方法是否被调用两次
        verify(test, times(2)).getUniqueId();

        // 其他用来验证函数是否被调用的方法
        verify(mock, never()).someMethod("never called");
        verify(mock, atLeastOnce()).someMethod("called at least once");
        verify(mock, atLeast(2)).someMethod("called at least twice");
        verify(mock, times(5)).someMethod("called five times");
        verify(mock, atMost(3)).someMethod("called at most 3 times");
}
```



## 3.4 使用Spy封装java对象

@Spy注解会真实地调用被注解对象的方法（而@Mock只是生成一个“假”的mock对象）

@Spy 或者`spy()`方法可以被用来封装 java 对象。被封装后，除非特殊声明（打桩 *stub*），否则都会真正的调用对象里面的每一个方法

```java
import static org.mockito.Mockito.*;

// Lets mock a LinkedList
List list = new LinkedList();
List spy = spy(list);

// 可用 doReturn() 来打桩
doReturn("foo").when(spy).get(0);

// 下面代码不生效
// 真正的方法会被调用
// 将会抛出 IndexOutOfBoundsException 的异常，因为 List 为空
when(spy.get(0)).thenReturn("foo");
```

## 3.5 使用 @InjectMocks 在 Mockito 中进行依赖注入

`@InjectMocks`，会根据类型来注入对象里面的成员方法和变量。

这个类会被 Mockito 构造，而类的成员方法和变量都会被 mock 对象所代替

更多的详情可以查看 http://docs.mockito.googlecode.com/hg/1.9.5/org/mockito/InjectMocks.html 



## 3.6 捕捉参数

`ArgumentCaptor`类允许我们在 verification 期间访问方法的参数。

```java
import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.util.Arrays;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class MockitoTests {
    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Captor
    private ArgumentCaptor<List<String>> captor;

    @Test
    public final void shouldContainCertainListItem() {
        List<String> asList = Arrays.asList("someElement_test", "someElement");
        final List<String> mockedList = mock(List.class);
        mockedList.addAll(asList);

        verify(mockedList).addAll(captor.capture());
        final List<String> capturedArgument = captor.getValue();
        assertThat(capturedArgument, hasItem("someElement"));
    }
}
```



## 3.7 Mockito 的限制

下面三种数据类型则不能够被测试

- final classes
- anonymous classes
- primitive types



## 3.8 使用 Powermock 来模拟静态方法

因为 Mockito 不能够 mock 静态方法，因此我们可以使用 `Powermock`。

```java
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;

@RunWith( PowerMockRunner.class )
@PrepareForTest( NetworkReader.class )
public class MyTest {

// 测试代码

 @Test
public void testSomething() {
    mockStatic( NetworkUtil.class );
  	//NetworkReader.getLocalHostname是一个static方法
    when( NetworkReader.getLocalHostname() ).andReturn( "localhost" );

    // 与 NetworkReader 协作的测试
}

```



## 3.9 如何mock new Date



```java
Date mockDate=new Date(1655372032027L);
PowerMockito.whenNew(Date.class).withNoArguments().thenReturn(mockDate);

```





## 3.10 参考链接

1.  [【Mockito】单元测试如何提升代码覆盖率](https://www.bilibili.com/video/BV1g94y1Z79d?spm_id_from=333.1007.top_right_bar_window_default_collection.content.click) 

# 4 jacoco
jacoco是常用UT覆盖率的组建


清理并重新生成jacoco的UT覆盖率报告步骤
1. 运行命令
```bash
mvn clean -B -U -T 2 package org.jacoco:jacoco-maven-plugin:prepare-agent
```
2. jacoco:report



# 5 参考文献

1.  [Mockito 应用指南](https://hezhiqiang8909.gitbook.io/java/docs/javalib/mockito) 
1.  [Mockito 教程](https://baeldung-cn.com/mockito-series) 
1.  [使用 JUnit 4 进行单元测试 - 教程](https://www.vogella.com/tutorials/JUnit4/article.html#introduction-to-testing) 
1. [JUnit 5 tutorial - Learn how to write unit tests](https://www.vogella.com/tutorials/JUnit/article.html#junitsetup) 
1. [IDEA中Junit单元测试的使用(初级篇)](https://blog.csdn.net/qq_36568192/article/details/79857185) 
1. [廖雪峰-编写JUnit测试](https://www.liaoxuefeng.com/wiki/1252599548343744/1304048154181666) 





