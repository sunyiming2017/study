package com.ymsun.study.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class CAS {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
//        自增并获取到这个值
        atomicInteger.incrementAndGet();
        Object o = new Object();
        System.out.println();
        /***
         * StringBuffer 是线程安全的，有synchronized修改append的方法，用于单线程效率不高
         *
         * StringBuilder 多用于单线程当中，是线程不安全的。但是在单线程中效率执行高
         */
        StringBuffer sb = new StringBuffer();
        sb.append(1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(1);
    }


}
