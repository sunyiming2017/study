package com.ymsun.study.modle;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author ymsun
 * @date 2020/8/17 17:34
 * 动态代理的实现
 */
public class ProxyModle {

    public static void main(String[] args) {
        Imessage imessage = (Imessage) new MessageProxy().bind(new MessageReal());
        imessage.send();
    }

}

interface Imessage{
    public void send();
}

class MessageReal implements Imessage{

    @Override
    public void send() {
        System.out.println("发送的真实消息。。。");
    }
}

class MessageProxy implements InvocationHandler{

    private Object target;  //保存真实业务主题对象

    /**
     * 进行真实业务对象与代理业务对象的绑定
     * @param target  表示真实业务对象
     * @return  返回的是代理业务对象
     */
    public Object bind(Object target){
        this.target = target;
        System.out.println(target.getClass().getClassLoader());
        System.out.println(Arrays.toString(target.getClass().getInterfaces()));
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用该方法");
        System.out.println(Arrays.toString(args));
        Object invoke = method.invoke(this.target, args);
        return invoke;
    }
}
