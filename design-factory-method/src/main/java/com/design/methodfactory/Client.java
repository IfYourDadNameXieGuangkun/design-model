package com.design.methodfactory;

import com.design.methodfactory.course.ICourse;
import com.design.methodfactory.courseFactory.JavaCourseFactory;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) {

        ICourse iCourse = new JavaCourseFactory().create();
        iCourse.record();
    }
}
