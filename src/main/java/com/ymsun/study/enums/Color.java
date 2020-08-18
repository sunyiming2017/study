package com.ymsun.study.enums;

/**
 * @author ymsun
 * @date 2020/8/10 15:39
 */
public enum Color {
    /**
     *
     */
        RED("红色"),BLANK("白色"),YELLOW("蓝色");

        private String name ;
        private Color(String name){
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

}
