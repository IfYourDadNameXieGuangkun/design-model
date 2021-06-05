package com.design.methodfactory.courseFactory;

import com.design.methodfactory.course.ICourse;
import com.design.methodfactory.course.JavaCourse;
import com.design.methodfactory.course.PythonCourse;

public class PythonCourseFactory implements ICourseFactory {
    @Override
    public ICourse create() {
        return new PythonCourse();
    }
}
