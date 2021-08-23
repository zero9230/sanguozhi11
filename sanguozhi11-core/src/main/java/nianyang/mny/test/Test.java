package nianyang.mny.test;

import org.junit.Before;
import org.junit.BeforeClass;

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
    public void test1() throws InterruptedException {
        long time1 = System.currentTimeMillis();
        Thread.sleep(50);
        long time2 = System.currentTimeMillis();
        System.out.println("测试时间："+(time2-time1)+"ms");
    }


}
