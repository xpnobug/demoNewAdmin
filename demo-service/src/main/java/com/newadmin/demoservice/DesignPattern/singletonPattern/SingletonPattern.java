package com.newadmin.demoservice.DesignPattern.singletonPattern;

/**
 * 单例模式的优点 控制实例数量：确保类只有一个实例，减少内存开销。 全局访问点：提供一个全局访问点，便于管理和访问。 延迟初始化：实例在第一次使用时才被创建，避免不必要的资源浪费。
 */
public class SingletonPattern {

    private static SingletonPattern instance;

    private SingletonPattern() {
    }

    /**
     * 确保一个类只有一个实例,并提供全局访问点。 在单例模式中，getInstance 方法用于获取单例类的唯一实例。 如果实例尚不存在（即 instance 为
     * null），则创建该实例；如果实例已经存在，则返回现有实例
     *
     * @return
     */
    public static SingletonPattern getInstance() {
        if (instance == null) {
            instance = new SingletonPattern();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello World!");
    }

}
