package com.ymsun.study.util;

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
//        String aa = "要加密的数据";
        String aa = "qwertyuiopasdfghjk";
        String encode = encode(aa);
        System.out.println(encode);
        String decode = decode(encode);

        System.out.println(decode);
        String ad = "WTFoa2JHTnVValZrVjJ4MlkwZEZiMHRUYjNGak1sSnRXakpvY1dGNmEzZE5lbEkzWmxoME9VMTZVamRhVjJoelRXcEJlVTFJTUQwPQ==";
        String sd = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCXb7k0m60YwCQDVrXaYF/uHc2qqRSMvFtwJkysYAtIFa07khXsG+KfuA01G/ymS0KseXCDrUfgTIIPo9JmGs4qrU+XaaUH6lV6/1ECHZ2C46rm/jCl4E5OqUVyuTt3in8qliMwnNhcnCDL2CfSwGivFPpwVe9liWhT+PKWjU6K/QIDAQAB";
        String sdf = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJdvuTSbrRjAJANWtdpgX+4dzaqpFIy8W3AmTKxgC0gVrTuSFewb4p+4DTUb/KZLQqx5cIOtR+BMgg+j0mYaziqtT5dppQfqVXr/UQIdnYLjqub+MKXgTk6pRXK5O3eKfyqWIzCc2FycIMvYJ9LAaK8U+nBV72WJaFP48paNTor9AgMBAAECgYA60jquJInnwzo50cRxPl4Er67jSymgQMmDllzR17yKE2FmcifxviMYxvTaXDSWo0USmVkKzJPOm+kpCgj/gAn0ECGx0ohfqEi0v02RpXM4sdx8slUYYjJWlfeQjWndpulwcTQFk4z+fiKNtmx8TY4JUN1WGL8zdCU26V1RShpwQQJBANflytzkGh3PWmhTmCe/17922yPHH8kguQkJcMMQK4KpHzsmiUxPdkvKkE95fdzir0SDecNVrzMPe61+zCHGRgcCQQCzkLfpVRm4KnD2OPByW4PUV57uqxC3p00ilgyIAVmzIPPBmXfFfKZ7xUx/47mDrlE+S8VGRzTGaSVh773LeYXbAkB+p8ll4s9Lc6yXDYtyrkH0idavW47TIEZM/ePQG3lbErpGqinbwbYlanVUmnJzFe06ZWMWa/r3Gns04fEBU18bAkEAifRPN1aIMfN5YdGQ+WrRt1ux4Q9QAqjoHABHlF3lOjfAziZH3FPN3B1Sjpa4OjC8fi3cXAfsxdqWO83idObiLQJAJovWTY4zQjTNk4LU5HSEqvyCW0/UV6XtPy29cTLAD13Q/hxy2Kj0+LL23wsrTrAzA/K2axoHYiX7+S6+FDoMUg==";
        System.out.println(ad.length());
        System.out.println(sd.length());
        System.out.println(sdf.length());

    }


}
