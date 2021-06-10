package com.design.proxy.静态代理v2;

/**
 * NO3 父亲对象
 */
public class Father {

    private Son son;

    //没办法扩展
    public Father(Son son) {
        this.son = son;
    }
    public void findLove(){
        System.out.println("父亲物色对象");
        this.son.findLove();
        System.out.println("双方同意交往,确立关系");
    }
}
