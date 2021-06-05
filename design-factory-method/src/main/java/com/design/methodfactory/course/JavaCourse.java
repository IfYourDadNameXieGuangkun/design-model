package com.design.methodfactory.course;

public class JavaCourse implements ICourse {
    @Override
    public void record() {
        System.out.println("java课程");
    }
}
