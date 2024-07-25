package com.newadmin.demoservice.DesignPattern.DecoratorPattern;

// 具体装饰者：加糖
public class SugarDecorator extends CoffeeDecorator {

    // 构造函数，传入被装饰的咖啡对象
    public SugarDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    // 重写cost方法，加上糖的价格
    @Override
    public double cost() {
        return super.cost() + 0.2; // 糖的价格为0.2
    }

    // 重写getDescription方法，加上糖的描述
    @Override
    public String getDescription() {
        return super.getDescription() + ", Sugar"; // 加糖的描述
    }
}
