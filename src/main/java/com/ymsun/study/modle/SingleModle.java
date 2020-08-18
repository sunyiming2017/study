package com.ymsun.study.modle;

/**
 * @author ymsun
 * @date 2020/8/17 11:16
 *
 * java中哪些场景用到了单例模式：Runtime类，Spring IOC控制反转
 * 对于多线程来说单例模式会不安全，单例只生成一个对象，
 * 但当多线程同时执行的时候会生成多个单例对象，违背了单例模式设计的初衷
 *
 * static的变量不受对象实例化的影响，在类一加载的过程中就对静态变量进行初始化，因此它的值不受实例化的影响
 * 主要是因为存储的空间不用,静态变量存储在Java虚拟机的方法区中，而实例化对象存在堆中。
 */
public class SingleModle {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++){
            new Thread(() -> {
                SingleTest instance = SingleTest.getInstance2();
                instance.aa();
            },"线程名-" + i).start();
        }
    }
}

class SingleTest{
    private static volatile SingleTest singleTest = null;

    private SingleTest(){
        System.out.println(Thread.currentThread().getName()+"生成一个单例对象");
    }

    /**
     * 不安全的单例设计
     * @return
     */
    public static SingleTest getInstance(){
        if (singleTest == null){
            singleTest = new SingleTest();
        }
        return singleTest;
    }

    /**
     * 安全式的单例设计
     * @return
     */
    public static synchronized SingleTest getInstance2(){
        if (singleTest == null){
            synchronized (SingleTest.class){
                if (singleTest == null){
                    singleTest = new SingleTest();
                }
            }
        }
        return singleTest;
    }
    public void aa(){
        System.out.println("生成一个单例实例对象！！！");
    }
}