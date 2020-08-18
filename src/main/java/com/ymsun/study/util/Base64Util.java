package com.ymsun.study.util;

import java.util.Arrays;
import java.util.Base64;

/**
 * @author ymsun
 * @date 2020/8/12 15:42
 */
public class Base64Util {

    //给加密字符串进行加盐设置
    private static final String SALT = "sunyiming";

    //设置加密次数
    public static final Integer REPEAT = 3;

    public static String encode(String str){
        String temp = str + "{" + SALT + "}";
        byte[] data = temp.getBytes(); //将字符串编程字节数组
        for (int x = 0; x < REPEAT; x++){
            data = Base64.getEncoder().encode(data);
        }
        return new String(data);
    }

    public static String decode(String str){
        byte[] data = str.getBytes();
        for (int i = 0; i < REPEAT; i++){
            data = Base64.getDecoder().decode(data);
        }
        System.out.println(data);
        //w+匹配字母数字和下划线
        return new String(data).replaceAll("\\{\\w+\\}","");
    }
    public static void main(String[] args) {
        String aa = "要加密的数据";

        String encode = encode(aa);
        System.out.println(encode);
        String decode = decode(encode);

        System.out.println(decode);

    }


}
