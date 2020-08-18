package com.ymsun.study.thread;

import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author ymsun
 * @date 2020/8/11 10:37
 * 多线程返回值的案例
 */
public class ThradTest implements Callable<String>{

        @Override
        public String call() throws Exception {
            for (int i = 0; i < 10; i++){
                System.out.println(Thread.currentThread().getName()+"-"+i);
                Thread.currentThread().getName();

            }
            return "多线程执行返回值成功！";
        }

}

class MyDemo{

    public static void main(String[] args) throws Exception{
        FutureTask<String> futureTask = new FutureTask<>(new ThradTest());
        new Thread(futureTask).start();
        new Thread(futureTask).start();
        boolean interrupted = new Thread(futureTask).isInterrupted();
        System.out.println(interrupted);
        System.out.println("执行多线程返回数据-"+ futureTask.get());
    }
}