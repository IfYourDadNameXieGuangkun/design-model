package com.design.钩子方法;

public class BlankBeanSoyaMilk extends SoyaMilk {
    @Override
    void addCondiments() {
        //do nothing
    }

    @Override
    Boolean customWant() {
        return false;
    }

    public static void main(String[] args) {
        SoyaMilk blankBeanSoyaMilk = new BlankBeanSoyaMilk();
        blankBeanSoyaMilk.make();
    }
}
