package com.design.钩子方法;


public abstract class SoyaMilk {

    //模板方法,final修饰,子类不能覆盖
    final void make() {
        select();
        if (customWant()) {
            addCondiments();
        }
        soak();
        beat();
    }

    //1.选材料
    private void select() {
        System.out.println("选材料");
    }

    //2.添加配料,抽象方法,子类去实现
    abstract void addCondiments();

    //3.浸泡
    private void soak() {
        System.out.println("浸泡");
    }

    //4.将配料和豆子放入豆浆机
    private void beat() {
        System.out.println("将配料和豆子放入豆浆机");
    }

    //5.钩子方法
    Boolean customWant() {
        return true;
    }

}
