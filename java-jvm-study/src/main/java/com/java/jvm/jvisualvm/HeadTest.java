package com.java.jvm.jvisualvm;

import java.util.ArrayList;

public class HeadTest {

    byte[] a = new byte[1024 * 100]; //100KB

    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeadTest> headTests = new ArrayList<>();
        while (true){
            headTests.add(new HeadTest());
            Thread.sleep(10);
        }

    }
}
