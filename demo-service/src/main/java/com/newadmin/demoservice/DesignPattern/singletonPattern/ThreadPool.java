package com.newadmin.demoservice.DesignPattern.singletonPattern;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 定义线程池类

/**
 * 确保线程池在整个应用程序中只有一个实例。这有助于有效地管理和调度线程， 并避免创建多个线程池实例带来的资源浪费和性能问题。 线程池可以显著提高应用程序的并发性能，并简化线程的管理
 */
public class ThreadPool {

    // 单例实例
    private static ThreadPool instance;
    // 线程池
    private ExecutorService executor;

    // 私有构造函数，防止外部实例化
    private ThreadPool() {
        // 创建一个固定大小的线程池，大小为10
        executor = Executors.newFixedThreadPool(10);
    }

    // 获取单例实例的方法
    public static synchronized ThreadPool getInstance() {
        if (instance == null) {
            instance = new ThreadPool();
        }
        return instance;
    }

    // 提交任务给线程池执行
    public void execute(Runnable task) {
        executor.execute(task);
    }

    // 关闭线程池
    public void shutdown() {
        executor.shutdown();
    }

    public static void main(String[] args) {
        // 获取线程池实例
        ThreadPool threadPool = ThreadPool.getInstance();

        // 提交任务给线程池执行
        for (int i = 0; i < 20; i++) {
            final int taskId = i;
            threadPool.execute(() -> {
                System.out.println(
                    "任务 " + taskId + " 正在线程中运行: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(2000); // 模拟任务执行时间
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(
                    "任务 " + taskId + " 在线程中完成: " + Thread.currentThread().getName());
            });
        }

        // 关闭线程池
        threadPool.shutdown();
    }
}
