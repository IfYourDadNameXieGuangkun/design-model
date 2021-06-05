package com.simple.factory.v2_用反射的方式优化代码.courseFactory;

import com.simple.factory.v2_用反射的方式优化代码.course.ICourse;

/**
 * 工厂类
 * 利用反射机制,新增课程时,不需要修改代码可得到对应的课程对象
 */
public class CourseFactory {
    public static ICourse createCourse(Class<? extends ICourse> clazz) {
        try {
            if (null != clazz) {
                return clazz.newInstance();
            }
        } catch (Exception e) {

        }
        return null;
    }
}
