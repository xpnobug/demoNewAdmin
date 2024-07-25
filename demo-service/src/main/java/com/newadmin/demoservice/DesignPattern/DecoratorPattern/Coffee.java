package com.newadmin.demoservice.DesignPattern.DecoratorPattern;

/**
 * 使用装饰者模式，我们可以灵活地为对象添加功能，而无需创建大量的子类。 这种模式尤其适用于功能扩展频繁变化的场景。通过动态地组合不同的装饰者， 我们可以构建出功能丰富且结构清晰的系统。
 */
// 抽象构件：咖啡接口
public interface Coffee {

    double cost(); // 获取咖啡的价格

    String getDescription(); // 获取咖啡的描述
}
