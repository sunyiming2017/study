package com.ymsun.study.thread;

import java.io.IOException;

/**
 * 懒汉式单例，实现线程安全性
 *
 * 注意事项：
 * 问题：为什么需要两次判断if(singleTon==null)?
 *
 * 分析：第一次校验：由于单例模式只需要创建一次实例，如果后面再次调用getInstance方法时，
 *      则直接返回之前创建的实例，因此大部分时间不需要执行同步方法里面的代码，大大提高了性能。
 *      如果不加第一次校验的话，那跟上面的懒汉模式没什么区别，每次都要去竞争锁。
 *
 * 　第二次校验：如果没有第二次校验，假设线程t1执行了第一次校验后，判断为null，
 *      这时t2也获取了CPU执行权，也执行了第一次校验，判断也为null。接下来t2获得锁，创建实例。
 *      这时t1又获得CPU执行权，由于之前已经进行了第一次校验，结果为null（不会再次判断），获得锁后，直接创建实例。结果就会导致创建多个实例。所以需要在同步代码里面进行第二次校验，如果实例为空，则进行创建。
 *
 * 需要注意的是，private static volatile SingleTon3 singleTon=null;
 *      需要加volatile关键字，否则会出现错误。问题的原因在于JVM指令重排优化的存在。
 *      在某个线程创建单例对象时，在构造方法被调用之前，就为该对象分配了内存空间并将对象的字段设置为默认值。
 *      此时就可以将分配的内存地址赋值给instance字段了，然而该对象可能还没有初始化。
 *      若紧接着另外一个线程来调用getInstance，取到的就是状态不正确的对象，程序就会出错。
 *
 * （4）静态内部类：同样也是利用了类的加载机制，它与饿汉模式不同的是，它是在内部类里面去创建对象实例。
 *      这样的话，只要应用中不使用内部类，JVM就不会去加载这个单例类，也就不会创建单例对象，
 *      从而实现懒汉式的延迟加载。也就是说这种方式可以同时保证延迟加载和线程安全。
 */
public class SingleLazy {
    /**
     * 美团会问加不加volatile关键字，答案是加，加的原因是什么
     *
     * 当我们在创建一个对象的时候，会经历哪些过程
     * 1. new 相当于c语言中malloc函数，功能是申请内存空间，设为成员变量的默认值，再建立关联
     *      如果在new的过程中发生了指令重排序，那么这个过程可能会造成线程安全的问题
     * 2. invokespecial 特殊调用，先new一个对象，调用其构造方法
     *
     * 3. astore_1
     *
     * 4.return
     */
    private static SingleLazy INSTANCE = null;

    private SingleLazy(){}

    public static SingleLazy getInstance(){
        if (INSTANCE == null){
            synchronized (SingleLazy.class){
                if (INSTANCE == null){
                    INSTANCE = new SingleLazy();
                    return INSTANCE;
                }
            }
        }
        return INSTANCE;
    }
}

class SingelHungry{
    private static final SingelHungry INSTANCE = new SingelHungry();

    private SingelHungry(){}

    public static SingelHungry getInstance(){
            return INSTANCE;
    }
}


class Exe{
    public static void main(String[] args) {
        long aa = 0;
        long bb = 0;
        for (int i = 0; i < 100; i ++){
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
//                    System.out.println("饿汉式："+SingelHungry.getInstance().hashCode());
                    System.out.println("懒汉式："+ SingleLazy.getInstance().hashCode());
                }
            });
            Thread two = new Thread(new Runnable() {
                @Override
                public void run() {
//                    System.out.println("饿汉式："+SingelHungry.getInstance().hashCode());
                    System.out.println("懒汉式："+SingleLazy.getInstance().hashCode());
                }
            });
            one.start();
            two.start();
        }
    }
}

