package com.ymsun.study.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ymsun
 * @date 2020/8/18 11:52
 */
public class AnnotationTest {
    public static void main(String[] args) throws Exception{
        //获取指定的方法
        Method method = Message.class.getMethod("send", String.class);
        //获取指定的注解
        MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
        System.out.println(annotation.title());
        System.out.println(annotation.url());
        //通过反射的方式调用方法
        method.invoke(Message.class.newInstance(),annotation.title());
        //使用annotation可以结合反射实现程序的处理
        method.invoke(Message.class.getDeclaredConstructor().newInstance(), annotation.title());
    }
}

/**
 * 自定义注解
 * @author User
 */
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation{

    public String title();

    public String url() default "xxx";

}

class Message{

    @MyAnnotation(title = "中国")
    public void send(String msg){
        System.out.println("发送的消息："+msg);
    }
}