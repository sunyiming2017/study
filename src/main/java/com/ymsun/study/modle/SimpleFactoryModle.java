package com.ymsun.study.modle;

/**
 * @author ymsun
 * @date 2020/8/17 10:16
 * 工厂模式
 */
public class SimpleFactoryModle {

    public static void main(String[] args) {
        Message message = MessageFactory.getInstance("cloudMessage");
        message.send();

        //参数必须输入Java类的完全限定名
        Message instanceV = MessageFactory.getInstanceV("com.ymsun.study.modle.NetMessage");
        Message instanceV1 = MessageFactory.getInstanceV("com.ymsun.study.modle.CloudMessage");
        instanceV.send();
        instanceV1.send();

        //通用的工程模式，泛型修饰
        Message instanceV2 = MessageFactory.getInstanceV2("com.ymsun.study.modle.NetMessage", Message.class);
        instanceV2.send();
    }

}

interface Message{

    public void send();

}

class NetMessage implements Message{
    public NetMessage(){}

    @Override
    public void send() {
        System.out.println("生产网络的消息");
    }

}

class CloudMessage implements Message{
    public CloudMessage(){}
    @Override
    public void send() {
        System.out.println("生产云的消息");
    }

}

class MessageFactory{
    private MessageFactory() {}

    /**
     *  简单的工厂模式的设计
     * @param msg
     * @return
     */
    public static Message getInstance(String msg){
        if ("netMessage".equals(msg)){
            return new NetMessage();
        }else if ("cloudMessage".equals(msg)){
            return new CloudMessage();
        }
        return null;
    }

    /**
     * 通过反射方式优化后的工厂模式的设计，写成之后新建一个子类不用在书写工厂代码
     * @param msg
     * @return
     */
    public static Message getInstanceV(String msg){
        Message message = null;
        try {
            message =(Message) Class.forName(msg).getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    /**
     * 构建一个通用的工厂模式，需要采用泛型类型
     * @param className 表示子类的名称
     * @param clazz     表示子类实现的父接口类型
     * @param <T>   与传入的父接口类型一致，而T代表的是方法返回的类型
     *           例如：String.class 的类型是Class<T>
     * @return
     */
    public static <T> T getInstanceV2(String className, Class<T> clazz){
        T t = null;
        try {
            t =(T) Class.forName(className).getConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}