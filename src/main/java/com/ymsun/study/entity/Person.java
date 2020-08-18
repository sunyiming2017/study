package com.ymsun.study.entity;

/**
 * @author ymsun
 * @date 2020/8/13 9:41
 */
public class Person implements Comparable<Person>{

    private String name;

    private int age;

    public Person(){}

    public Person(String name , int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }


    @Override
    public int compareTo(Person o) {
        int flag = this.age - o.age;
        if (flag == 0){
            return 0;
        }else if (flag > 0){
            return 1;
        }else if (flag < 0){
            return -1;
        }
        return 0;
    }
}
