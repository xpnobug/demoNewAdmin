package com.newadmin.demoservice.DesignPattern.factory;

// 第二个具体工厂
public class ConcreteFactoryB implements Factory {

    @Override
    public Product createProduct() {
        return new ConcreteProductB();
    }
}
