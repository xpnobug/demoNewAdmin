package com.newadmin.demoservice.DesignPattern.DecoratorPattern;

// 装饰者抽象类，实现了Coffee接口
public abstract class CoffeeDecorator implements Coffee {

    protected Coffee decoratedCoffee; // 被装饰的咖啡对象

    // 构造函数，传入被装饰的咖啡对象
    public CoffeeDecorator(Coffee decoratedCoffee) {
        this.decoratedCoffee = decoratedCoffee;
    }

    // 实现cost方法，调用被装饰对象的cost方法
    @Override
    public double cost() {
        return decoratedCoffee.cost();
    }

    // 实现getDescription方法，调用被装饰对象的getDescription方法
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
}

