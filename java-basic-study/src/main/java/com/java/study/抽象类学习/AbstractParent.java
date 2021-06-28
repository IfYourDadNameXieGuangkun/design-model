package com.java.study.抽象类学习;

public abstract class AbstractParent {
    private String name;
    private String hobbies;

    protected AbstractParent(String name, String hobbies) {
        this.name = name;
        this.hobbies = hobbies;
    }

    abstract String eat();
}
