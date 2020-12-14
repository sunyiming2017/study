package com.ymsun.study.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 老外写的一个小程序证明cpu是指令乱序的
 * 内存屏障
 * volatile ： 禁止指令重排序
 *
 * volatile: 具有可见性 ，会发生回写， 首先会从主内存复制一份数据到自己的工作内存，当在自己的工作内存修改
 * 时，会发生回写， 然后其他的线程会看到修改的值。修改的过程会发生什么呢？首先会先在主内存加锁，防止其他线程
 * 进来修改，然后会通过执行引擎完成主内存和工作内存的调度，回写完成之后然后会释放锁。
 */
public class VolatileStudy {

    private static int x = 0, y = 0;
    private static int a= 0, b = 0;
    private volatile AtomicInteger atomicInteger = new AtomicInteger();
    private ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
    public static void main(String[] args) throws InterruptedException {

        long l1 = System.currentTimeMillis();
        int i = 0;
        for (;;){
            i++;
            x=0;y=0;
            a=0;b=0;
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    x = b;
                }
            });

            Thread two = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    y = a;
                }
            });

            one.start();
            two.start();
            one.join();
            two.join();
            String result = "第"+i+"次"+"x="+x+"  "+"y="+y;
            if (x == 0 && y==0){
                System.out.println(result);
                long l2 = System.currentTimeMillis();
                System.out.println((l2 - l1)/1000.0+"秒");
                break;
            }else {

            }
        }

    }

}
