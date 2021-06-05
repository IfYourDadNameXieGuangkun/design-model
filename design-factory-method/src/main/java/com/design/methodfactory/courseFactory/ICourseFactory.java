package com.design.methodfactory.courseFactory;

import com.design.methodfactory.course.ICourse;

/**
 * 工厂类
 * 将create方法变为静态,方便调用
 */
public interface ICourseFactory {
    public ICourse create();
}
