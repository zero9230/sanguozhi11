package nianyang.mny.test;

import org.junit.Before;
import org.junit.BeforeClass;

import java.math.BigDecimal;

public class Test {

    @BeforeClass
    public static void beforeClassTest(){

        System.out.println("单元测试开始前执行初始化");
    }

    @Before
    public void beforeTest(){
        System.out.println("单元测试开始前");
    }

    @org.junit.Test
    public void test1()  {

        System.out.println("hello");

        if(true) {
            throw new RuntimeException("111");
        }

        System.out.println("hello1");




    }


}
