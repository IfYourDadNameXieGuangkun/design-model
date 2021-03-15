package com.design;

public class ReadBeanSoyaMilk extends SoyaMilk {
    @Override
    void addCondiments() {
        System.out.println("ReadBeanSoyaMilk-->添加红糖变红豆浆");
    }

    public static void main(String[] args) {
        new ReadBeanSoyaMilk().make();
    }
}
