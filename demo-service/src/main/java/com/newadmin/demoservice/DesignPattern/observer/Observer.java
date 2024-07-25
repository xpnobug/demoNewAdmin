package com.newadmin.demoservice.DesignPattern.observer;

// 观察者接口
public interface Observer {

    void update(String message); // 更新方法，接收主题发来的消息
}
