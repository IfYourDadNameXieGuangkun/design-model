package com.design.钩子方法;

public class BlackBeanSoyaMilk extends SoyaMilk {
    @Override
    void addCondiments() {
        System.out.println("BlackBeanSoyaMilk-->添加黑糖变黑豆浆");
    }


    public static void main(String[] args) {
        SoyaMilk blackBeanSoyaMilk  = new BlackBeanSoyaMilk();
        blackBeanSoyaMilk.make();
    }
}
