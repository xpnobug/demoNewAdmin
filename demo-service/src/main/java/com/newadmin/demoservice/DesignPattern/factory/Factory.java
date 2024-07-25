package com.newadmin.demoservice.DesignPattern.factory;

// 工厂接口
public interface Factory {

    Product createProduct(); // 定义创建产品的方法
}
/**
 * 通过使用工厂方法模式，我们可以动态创建对象，并确保对象的创建逻辑集中在一个地方。 这有助于提高代码的灵活性和可维护性，特别是在需要根据不同条件创建不同对象的情况下。
 * 工厂方法模式还可以避免在代码中到处使用 new 操作符，从而使代码更简洁、更易读。
 */
