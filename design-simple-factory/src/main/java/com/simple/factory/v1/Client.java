package com.simple.factory.v1;

import com.simple.factory.v1.course.ICourse;
import com.simple.factory.v1.courseFactory.CourseFactory;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) {

        ICourse java = CourseFactory.createCourse("java");
        java.record();
    }
}
