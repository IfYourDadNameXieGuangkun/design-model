package com.observe;

import java.util.Observable;
import java.util.Observer;

public class Teacher implements Observer {

    private String userName;

    public Teacher(String userName) {
        this.userName = userName;
    }

    @Override
    public void update(Observable o, Object arg) {
        Gper gper = (Gper) o;
        Question question = (Question) arg;
        System.out.println(this.userName + "老师,您收到来自" + question.getUserName() + "同学的问题" + question.getContent());
    }
}
