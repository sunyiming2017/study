package com.ymsun.study.entity;

import java.util.Comparator;

/**
 * @author ymsun
 * @date 2020/8/13 10:21
 */
public class PersonComporator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return o1.compareTo(o2);
    }
}
