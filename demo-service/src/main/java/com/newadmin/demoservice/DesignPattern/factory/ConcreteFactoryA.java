package com.newadmin.demoservice.DesignPattern.factory;

// 第一个具体工厂
public class ConcreteFactoryA implements Factory {

    @Override
    public Product createProduct() {
        return new ConcreteProductA();
    }
}

