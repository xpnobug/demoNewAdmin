package com.newadmin.demoservice.DesignPattern.factory;

// 第一个具体产品
public class ConcreteProductA implements Product {

    @Override
    public void use() {
        System.out.println("Using ConcreteProductA");
    }
}

