package com.design.浅克隆;

import java.util.ArrayList;
import java.util.List;

public class PrototypeTest {
    public static void main(String[] args) {
        ConcretePrototypeA concretePrototype = new ConcretePrototypeA();
        concretePrototype.setAge(18);
        concretePrototype.setName("张三");
        List<String> hobbies = new ArrayList<>();
        hobbies.add("唱");
        hobbies.add("跳");
        hobbies.add("rap");
        concretePrototype.setHobbies(hobbies);
        System.out.println(concretePrototype);

        //创建Client对象,准备开始克隆
        Client client = new Client(concretePrototype);
        ConcretePrototypeA clone = (ConcretePrototypeA) client.startClone(concretePrototype);
        System.out.println(clone);
        System.out.println("对象地址"+concretePrototype.getHobbies().hashCode());
        System.out.println("克隆对象地址"+clone.getHobbies().hashCode());
        System.out.println("对比:"+(concretePrototype.getHobbies()==clone.getHobbies()));//true 对比地址
    }
}
