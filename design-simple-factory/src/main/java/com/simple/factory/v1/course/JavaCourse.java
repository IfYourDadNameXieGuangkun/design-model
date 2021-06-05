package com.simple.factory.v1.course;

public class JavaCourse implements ICourse {
    @Override
    public void record() {
        System.out.println("java课程");
    }
}
