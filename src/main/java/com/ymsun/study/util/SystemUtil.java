package com.ymsun.study.util;

import java.io.File;
import java.io.IOException;

/**
 * @author ymsun
 * @date 2020/11/27 10:23
 * @description: TODO
 */
public class SystemUtil {
    public static String getSystemRoot() throws IOException {
//        String osName = System.getProperty("os.name");
//        System.out.println(osName);
//        if (osName.toLowerCase().startsWith("mac")) {
//            return "/usr/local/xx/";
//        } else if (osName.toLowerCase().startsWith("windows")) {
//            return "C:\\tiger\\xx\\";
//        } else {
//            return "/User/local/xx/";
//        }

        File file = new File("D:\\dist\\aa");
        boolean exists = file.exists();
        System.out.println(exists);
        if(!file.exists()){
            if (!file.createNewFile()) {
            }
        }
        return "success";
    }

    public static void main(String[] args) throws IOException {
        String systemRoot = getSystemRoot();
        System.out.println(systemRoot);
    }
}
