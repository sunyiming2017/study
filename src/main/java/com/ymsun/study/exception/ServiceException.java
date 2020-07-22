package com.ymsun.study.exception;

/**
 * @author ymsun
 * @date 2020/7/20 17:56
 */
public class ServiceException extends Exception{
    String message;

    public ServiceException(){}

    public ServiceException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
