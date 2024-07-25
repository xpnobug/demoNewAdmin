package com.newadmin.demoservice.DesignPattern.singletonPattern;

// 计数器类

/**
 * 全局计数器，确保在应用程序的生命周期内只有一个计数器实例。 这有助于在多线程环境中安全地统计访问次数或跟踪特定事件的发生次数。
 * 计数器类提供了基本的计数操作，如增加计数、获取当前计数和重置计数。
 */
public class Counter {

    // 单例实例
    private static Counter instance;
    // 计数器变量
    private int count;

    // 私有构造函数，防止外部实例化
    private Counter() {
        count = 0;
    }

    // 获取单例实例的方法
    public static synchronized Counter getInstance() {
        if (instance == null) {
            instance = new Counter();
        }
        return instance;
    }

    // 增加计数
    public synchronized void increment() {
        count++;
    }

    // 获取当前计数
    public synchronized int getCount() {
        return count;
    }

    // 重置计数
    public synchronized void reset() {
        count = 0;
    }

    public static void main(String[] args) {
        // 获取计数器实例
        Counter counter = Counter.getInstance();

        // 模拟一些事件发生，增加计数
        for (int i = 0; i < 10; i++) {
            counter.increment();
            System.out.println("Current count: " + counter.getCount());
        }

        // 重置计数
        counter.reset();
        System.out.println("Count after reset: " + counter.getCount());
    }
}
