package com.thread.type03;

import java.util.concurrent.Callable;

public class ThreadDemo03 implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "这是现场翁";
    }
}
