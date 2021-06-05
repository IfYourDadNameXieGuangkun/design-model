package com.simple.factory.v2_用反射的方式优化代码;

import com.simple.factory.v2_用反射的方式优化代码.course.ICourse;
import com.simple.factory.v2_用反射的方式优化代码.course.JavaCourse;
import com.simple.factory.v2_用反射的方式优化代码.courseFactory.CourseFactory;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) {
        ICourse java = CourseFactory.createCourse(JavaCourse.class);
        java.record();
    }
}
