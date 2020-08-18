package com.ymsun.study.thread;

/**
 * @author ymsun
 * @date 2020/8/12 9:16
 */

class DemoTest{
    public static void main(String[] args) {
        Resource resource = new Resource();
        AddThread addThread = new AddThread(resource);
        SubThread subThread = new SubThread(resource);
        Thread t1 = new Thread(addThread,"加法操作-A");
        Thread t2 = new Thread(addThread,"加法操作-B");
        Thread t3 = new Thread(subThread,"减法操作-X");
        Thread t4 = new Thread(subThread,"减法操作-Y");
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }
}

   class  AddThread implements Runnable{
        private Resource resource;

        public AddThread(Resource resource){
            this.resource = resource;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50 ; i++){
                try {
                    resource.add();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class  SubThread implements Runnable{
        private Resource resource;

        public SubThread(Resource resource){
            this.resource = resource;
        }

        @Override
        public void run() {
            for (int i = 0; i < 50 ; i++){
                try {
                    resource.sub();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


     class Resource {
        private int num = 0;
        private boolean flag = true;

        public synchronized void add() throws Exception{
            if (flag == false){
                super.wait();
            }
            Thread.sleep(100);
            this.num ++;
            System.out.println("【加法操作- " + Thread.currentThread().getName() + "】num = " + this.num);
            this.flag = false;  //加法操作执行完毕
            super.notify();
        }

        public synchronized void sub() throws Exception{
            if (flag == true){
                super.wait();
            }
            Thread.sleep(100);
            this.num --;
            System.out.println("【减法操作- " + Thread.currentThread().getName() + "】num = " + this.num);
            this.flag = true;  //减法操作执行完毕
            super.notify();
        }
    }


