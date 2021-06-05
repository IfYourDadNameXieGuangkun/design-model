package com.design.methodfactory.courseFactory;

import com.design.methodfactory.course.ICourse;
import com.design.methodfactory.course.JavaCourse;

public class JavaCourseFactory implements ICourseFactory {
    @Override
    public ICourse create() {
        return new JavaCourse();
    }
}
