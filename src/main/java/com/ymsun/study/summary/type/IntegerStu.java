package com.ymsun.study.summary.type;

import com.ymsun.study.util.FileUtils;

import java.math.BigDecimal;

/**
 * @author ymsun
 * @date 2020/7/16 16:14
 */
public class IntegerStu {
    public static void main(String[] args) {
        /**
         * 由于Integer整型的缓存作用范围是-128-127，所以超过以外的数要注意
         * 因为地址空间不同，两者再使用==号判断时，结果为false
         */
        Integer i1 = 300;
        Integer i2 = 300;
        System.out.println(i1 == i2);
        System.out.println(i1.equals(i2));

        //浮点数之间不能用==做判断，它们之间有一定的科学计数
        BigDecimal a = new BigDecimal("1.0");
        BigDecimal b = new BigDecimal("0.9");
        BigDecimal c = new BigDecimal("0.8");
        BigDecimal x = a.subtract(b);
        BigDecimal y = b.subtract(c);
        if (x.equals(y)) {
            System.out.println("true");
        }
        //这样转换会存在一个精度问题，最终返回值不是0.1，而是0.100000001490116119384765625
        BigDecimal g = new BigDecimal(0.1f);
        //正确方式
        BigDecimal recommend1 = new BigDecimal("0.1");
        BigDecimal recommend2 = BigDecimal.valueOf(0.1);
        System.out.println(g+":"+recommend1+":"+recommend2);

        System.out.println(Integer.MAX_VALUE);

        FileUtils fileUtils = new FileUtils();
        fileUtils.aa();
    }
}
