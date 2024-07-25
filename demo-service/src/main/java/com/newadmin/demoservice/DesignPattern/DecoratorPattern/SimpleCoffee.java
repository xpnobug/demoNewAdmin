package com.newadmin.demoservice.DesignPattern.DecoratorPattern;

// 具体构件：普通咖啡
public class SimpleCoffee implements Coffee {

    @Override
    public double cost() {
        return 1.0; // 普通咖啡的价格为1.0
    }

    @Override
    public String getDescription() {
        return "简单的咖啡"; // 普通咖啡的描述为“普通咖啡”
    }
}
