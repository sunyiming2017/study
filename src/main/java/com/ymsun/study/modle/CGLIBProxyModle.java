package com.ymsun.study.modle;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author ymsun
 * @date 2020/8/18 9:26
 * CGLIB代理类的创建，不需要实现接口，不强制的与接口进行绑定
 */
public class CGLIBProxyModle {
    public static void main(String[] args) {
        Imessage messageReal = new MessageReal();    //真实主体对象
        Enhancer enhancer = new Enhancer();     //负责代理操作的程序类
        enhancer.setSuperclass(messageReal.getClass());     //假定一个父类
        enhancer.setCallback(new CommonProxy(messageReal));     //设置代理类
        Imessage proxy = (Imessage)enhancer.create();   //创建代理对象
        MessageReal proxy1 = (MessageReal)enhancer.create();   //创建代理对象
        proxy.send();
        proxy1.send();
        messageReal.send();  //创建了真实类的对象，我直接调用就行，还用的着动态代理类多此一举的调用吗？？？
    }
}

class  CommonProxy implements MethodInterceptor{    //拦截器配置

    private  Object target;     //保存真实主体对象

    public CommonProxy(Object target){
        this.target = target;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object returnData = null;
        if (this.connect()){
            returnData = method.invoke(this.target, args);
            this.close();
        }
        return null;
    }

    public boolean connect(){
        System.out.println("【消息代理】建立连接。。。");
        return true;
    }

    public void close(){
        System.out.println("【消息代理】关闭连接。。。");
    }
}