package com.newadmin.demoservice.DesignPattern.observer;

import java.util.ArrayList;
import java.util.List;

// 具体目标类
public class ConcreteSubject implements Subject {

    private List<Observer> observers = new ArrayList<>();
    private String message;

    // 注册观察者
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    // 移除观察者
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // 通知观察者
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    // 设置状态变化，触发通知
    public void setMessage(String message) {
        this.message = message;
        notifyObservers();
    }
}
