package com.newadmin.demoservice.DesignPattern.observer;

/**
 * 观察者模式允许对象之间建立一种一对多的依赖关系，当一个对象的状态发生变化时，所有依赖它的对象都将得到通知并自动更新。 这种模式在事件驱动系统、消息发布和订阅、UI组件更新等场景中非常有用。
 * 通过将观察者模式应用于程序中，可以实现对象间的松耦合，提高系统的灵活性和可维护性。
 */
// 目标接口
public interface Subject {

    void registerObserver(Observer observer); // 注册观察者

    void removeObserver(Observer observer);  // 移除观察者

    void notifyObservers();                 // 通知观察者
}
