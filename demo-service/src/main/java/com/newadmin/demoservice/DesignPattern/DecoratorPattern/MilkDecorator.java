package com.newadmin.demoservice.DesignPattern.DecoratorPattern;

// 具体装饰者：加牛奶
public class MilkDecorator extends CoffeeDecorator {

    // 构造函数，传入被装饰的咖啡对象
    public MilkDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    // 重写cost方法，加上牛奶的价格
    @Override
    public double cost() {
        return super.cost() + 0.5; // 牛奶的价格为0.5
    }

    // 重写getDescription方法，加上牛奶的描述
    @Override
    public String getDescription() {
        return super.getDescription() + ", Milk"; // 加牛奶的描述
    }
}

