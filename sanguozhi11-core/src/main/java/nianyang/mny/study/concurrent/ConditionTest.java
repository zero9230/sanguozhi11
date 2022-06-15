package nianyang.mny.study.concurrent;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author nmeng
 * @Date 2022/5/20 23:40
 **/

public class ConditionTest {
    @Test
    public void conditionTest(){
        Lock lock=new ReentrantLock();
        Condition condition = lock.newCondition();
    }
}
