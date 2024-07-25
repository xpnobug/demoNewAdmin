package com.newadmin.demoservice.DesignPattern.observer;

// 具体观察者类
public class ConcreteObserver implements Observer {

    private String name;

    // 构造函数
    public ConcreteObserver(String name) {
        this.name = name;
    }

    // 更新方法，处理主题通知
    @Override
    public void update(String message) {
        System.out.println(name + " 收到的消息: " + message);
    }
}
