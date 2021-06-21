package com.observe;

public class TestObserver {
    public static void main(String[] args) {
        Teacher tom = new Teacher("tom");
        Teacher jack = new Teacher("jack");

        Question question = new Question("阿夜", "什么是观察者模式");
        Gper gper = new Gper();
        gper.addObserver(tom);
        gper.addObserver(jack);
        gper.publishQuestion(question);
    }
}
