package com.ymsun.study.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author ymsun
 * @date 2020/8/12 10:53
 * todo 主要是为了测试多线程中Callable带有的返回值
 */
public class RushAnswer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread m = new MyThread();
        FutureTask<String> futureTaskA = new FutureTask<String>(m);
        FutureTask<String> futureTaskB = new FutureTask<String>(m);
        FutureTask<String> futureTaskC = new FutureTask<String>(m);
        new Thread(futureTaskA,"竞争者A").start();
        new Thread(futureTaskB,"竞争者B").start();
        new Thread(futureTaskC,"竞争者C").start();
        System.out.println(futureTaskA.get());
        System.out.println(futureTaskB.get());
        System.out.println(futureTaskC.get());
    }
}

class MyThread implements Callable<String>{

    private boolean flag = false;

    @Override
    public String call() throws Exception {
        if (this.flag == false){
            this.flag = true;
            return Thread.currentThread().getName() + "抢答成功！";
        }else {
            return Thread.currentThread().getName() + "抢答失败！";
        }
    }
}