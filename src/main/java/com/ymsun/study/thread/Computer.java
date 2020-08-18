package com.ymsun.study.thread;

/**
 * @author ymsun
 * @date 2020/8/12 9:59
 */
public class Computer {

    private static int count = 0; //表示生产的个数
    private String name;
    private double price;
    public Computer(String name, double price){
        this.name = name;
        this.price = price;
        count ++;
    }

    @Override
    public String toString() {
        return "[第"+count+"台电脑，" + "电脑的名字：" + this.name + "、价值：" + this.price ;
    }
}


class ResourceTwo{
    private boolean flag = true;
    private Computer computer;
    public synchronized void product() throws Exception{
        if (this.computer != null){
            super.wait();
        }

        this.computer = new Computer("联想电脑",1.1);
        System.out.println(Thread.currentThread().getName() + ":" +"生产："+this.computer);
        super.notifyAll();
    }


    public synchronized void consumer() throws Exception{
        if (this.computer == null){
            super.wait();
        }
        Thread.sleep(300);
        System.out.println(Thread.currentThread().getName() + ":" +"消费："+this.computer);
        this.computer = null;
        super.notifyAll();
    }

}

class Producer implements Runnable{
    private ResourceTwo resourceTwo;

    public  Producer(ResourceTwo resourceTwo){
        this.resourceTwo = resourceTwo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50 ; i++){
            try {
                this.resourceTwo.product();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer implements Runnable{
    private ResourceTwo resourceTwo;

    public  Consumer(ResourceTwo resourceTwo){
        this.resourceTwo = resourceTwo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50 ; i++){
            try {
                this.resourceTwo.consumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


class TestTwo {
    public static void main(String[] args) {
        ResourceTwo resourceTwo = new ResourceTwo();
//        Thread t1 = new Thread(new Producer(resourceTwo));
        Thread t2 = new Thread(new Producer(resourceTwo));
//        Thread t3 = new Thread(new Consumer(resourceTwo));
        Thread t4 = new Thread(new Consumer(resourceTwo));
//        t1.start();
        t2.start();
//        t3.start();
        t4.start();


    }
}