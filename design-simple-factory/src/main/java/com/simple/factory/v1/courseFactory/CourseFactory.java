package com.simple.factory.v1.courseFactory;

import com.simple.factory.v1.course.ICourse;
import com.simple.factory.v1.course.JavaCourse;
import com.simple.factory.v1.course.PythonCourse;

/**
 * 工厂类
 * 将create方法变为静态,方便调用
 */
public class CourseFactory {
    public static ICourse createCourse(String name) {
        if ("java".equals(name)) {
            return new JavaCourse();
        } else if ("python".equals(name)) {
            return new PythonCourse();
        }
        return null;
    }
}
