package com.core.make2.spring.framework.context.support;

/**
 * IoC 容器实现类的顶层抽象类，实现 IoC 容器相关的公共逻辑。为
 * 了尽可能地简化，在这个Mini版本中，暂时只设计了一个refresh（）方
 * 法。
 */
public abstract class GPAbstractApplicationContext {
    public void refresh() throws Exception{}
}
