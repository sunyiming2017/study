package com.ymsun.study.entity;

import lombok.Data;

/**
 * @author ymsun
 * @date 2020/7/20 17:09
 */
public class User {

    /**
     * 用户名
     */
    private String name;

    private Boolean isBool;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getBool() {
        return isBool;
    }

    public void setBool(Boolean bool) {
        isBool = bool;
    }
}
