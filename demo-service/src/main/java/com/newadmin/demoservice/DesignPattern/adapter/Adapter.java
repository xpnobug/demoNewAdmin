package com.newadmin.demoservice.DesignPattern.adapter;

// 适配器类

/**
 * 使用适配器模式，我们可以将不兼容的接口适配到一起，使得原本无法协同工作的类能够一起工作。
 * 适配器模式有助于复用现有类的功能，而不需要修改其代码，并能使客户端代码与具体实现解耦，从而提高系统的灵活性和可维护性。 这种模式在处理第三方库、遗留系统以及不同数据格式时尤为有用。
 */
public class Adapter implements Target {

    private Adaptee adaptee;

    // 构造函数，接受一个现有类的实例
    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    // 实现目标接口的方法，并调用现有类的特定方法
    @Override
    public void request() {
        adaptee.specificRequest();
    }
}
