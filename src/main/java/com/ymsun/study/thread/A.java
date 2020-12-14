package com.ymsun.study.thread;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 强软弱虚
 */
class A {
    public static void main(String[] args) throws IOException {
        /**
         * 强引用
         * aa 指向 AA
         * 当aa为null时，会被垃圾回收机制回收
         */
        System.out.println("强引用");
        A aa = new A();
        aa = null;
        System.gc();
//        System.in.read();
//        软引用
        System.out.println("软引用");
        SoftReference<byte[]> s = new SoftReference(new Byte[1024]);
        System.out.println(s.get());
        System.gc();
        System.out.println(s.get());
        //此时将堆内存进行装满，强制清除前面的内存，所以w对象会被清除
        byte[] b = new byte[1024 * 1024 * 15];
        System.out.println(s.get());

//      弱引用:  只要有垃圾回收机制，就能直接把w回收
        System.out.println("弱引用");
        WeakReference<M> w = new WeakReference(new M());
        System.out.println(w.get());
        System.gc();
        //此时已经调用了垃圾回收机制，但是该对象并没有回收
        System.out.println(w.get());


        /**
         * 虚引用：是get不到的，不管它被不被垃圾回收机制回收
         *
         * 虚引用管理堆外内存
         *
         */

    }

}
