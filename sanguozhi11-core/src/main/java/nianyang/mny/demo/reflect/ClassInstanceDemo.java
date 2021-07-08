package nianyang.mny.demo.reflect;

import nianyang.mny.leetcode.P15;

import java.lang.reflect.Field;

public class ClassInstanceDemo {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Class<P15> p15Class = P15.class;
        P15 p15 = p15Class.newInstance();
        Field test = p15Class.getField("t");

    }
}
