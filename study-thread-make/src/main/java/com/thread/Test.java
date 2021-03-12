package com.thread;

import com.thread.type03.ThreadDemo03;
import com.thread.type1.ThreadDemo01;
import com.thread.type2.ThreadDemo02;

import java.util.concurrent.FutureTask;

public class Test {
    public static void main(String[] args) {
        ThreadDemo01 thread01 = new ThreadDemo01("thread01");
        thread01.start();
        Thread thread02 = new Thread(new ThreadDemo02(),"thread02");
        thread02.start();


    }
}
