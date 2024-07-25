package com.newadmin.demoservice.DesignPattern.factory;

// 第二个具体产品
public class ConcreteProductB implements Product {

    @Override
    public void use() {
        System.out.println("Using ConcreteProductB");
    }
}
