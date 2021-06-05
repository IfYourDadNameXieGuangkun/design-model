package com.simple.factory.v1.course;

public class PythonCourse implements ICourse {
    @Override
    public void record() {
        System.out.println("Python课程");
    }
}
