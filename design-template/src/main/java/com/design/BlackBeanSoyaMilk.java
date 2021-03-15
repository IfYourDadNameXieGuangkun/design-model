package com.design;

public class BlackBeanSoyaMilk extends SoyaMilk {
    @Override
    void addCondiments() {
        System.out.println("BlackBeanSoyaMilk-->添加黑糖变黑豆浆");
    }


    public static void main(String[] args) {
        new BlackBeanSoyaMilk().make();
    }
}
