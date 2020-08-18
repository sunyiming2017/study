package com.ymsun.study.modle;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ymsun
 * @date 2020/8/18 12:19
 * 工厂模式、代理模式、反射的综合整理实现
 */
public class FactoryAnnotaionTest {
    public static void main(String[] args) {
//        第一种实现方式
//        IMsg iMsg = FactoryDesign.getInstance(Msg.class);
//        iMsg.send("哈哈哈");

//        第二种实现方式
//        MsgFactoryService msgFactoryService = new MsgFactoryService(NetMsg.class);
//        msgFactoryService.send("哈哈哈");

//        第三种通过注解方式实现调用
        MsgAnnService msgAnnService = new MsgAnnService();
        msgAnnService.send("哈哈哈");



    }
}

/**
 * @author User
 */
@Retention(RetentionPolicy.RUNTIME)
@interface UseMessage{
    public Class<?> clazz();
}

/**
 * 通过注解方式实现调用
 */
@UseMessage(clazz = Msg.class)
class MsgAnnService {
    private IMsg iMsg;

    public MsgAnnService(){
        UseMessage annotation = MsgAnnService.class.getAnnotation(UseMessage.class);
        this.iMsg = (IMsg) FactoryDesign.getInstance(annotation.clazz());
    }

    public void send(String msg){
        this.iMsg.send(msg);
    }

}

/**
 * 通过工厂模式的调用
 */
class MsgFactoryService {
    private IMsg iMsg;

    public MsgFactoryService(Class<?> tClass){
        this.iMsg = (IMsg) FactoryDesign.getInstance(tClass);
    }

    public void send(String msg){
        this.iMsg.send(msg);
    }

}

class FactoryDesign{
    private FactoryDesign(){}

    public static <T> T getInstance(Class<T> tClass){   //直接返回一个实例化对象
        try {
            return (T) new MsgProxy().bind(tClass.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
class MsgProxy implements InvocationHandler{

    private Object target;

    public Object bind(Object target){
        this.target = target;
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public boolean conn(){
        System.out.println("【建立连接】");
        return true;
    }

    public void close(){
        System.out.println("【关闭连接】");
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (this.conn()){
                return method.invoke(this.target, args);
            }else {
                throw new Exception("消息无法进行发送！！！");
            }
        }finally {
            this.close();
        }
    }
}

interface IMsg{
    public void send(String msg);
}

class Msg implements IMsg{

    @Override
    public void send(String msg) {
        System.out.println("【消息发送】" + ":" + msg );
    }
}

class NetMsg implements IMsg{

    @Override
    public void send(String msg) {
        System.out.println("【网络消息发送】" + ":" + msg );
    }
}