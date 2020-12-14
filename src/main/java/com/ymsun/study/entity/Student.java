package com.ymsun.study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Student implements Serializable {

//    public Student(){}

    private String name;

    private Integer sex;

    private Date date;
}

class MM{
    public static void main(String[] args) {
        Student aa = Student.builder().name("aa").build();
        System.out.println(aa);
    }
}
