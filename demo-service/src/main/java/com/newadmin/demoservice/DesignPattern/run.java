package com.newadmin.demoservice.DesignPattern;

import com.newadmin.demoservice.DesignPattern.DecoratorPattern.Coffee;
import com.newadmin.demoservice.DesignPattern.DecoratorPattern.MilkDecorator;
import com.newadmin.demoservice.DesignPattern.DecoratorPattern.SimpleCoffee;
import com.newadmin.demoservice.DesignPattern.DecoratorPattern.SugarDecorator;
import com.newadmin.demoservice.DesignPattern.adapter.Adaptee;
import com.newadmin.demoservice.DesignPattern.adapter.Adapter;
import com.newadmin.demoservice.DesignPattern.adapter.Target;
import com.newadmin.demoservice.DesignPattern.factory.ConcreteFactoryA;
import com.newadmin.demoservice.DesignPattern.factory.ConcreteFactoryB;
import com.newadmin.demoservice.DesignPattern.factory.Factory;
import com.newadmin.demoservice.DesignPattern.factory.Product;
import com.newadmin.demoservice.DesignPattern.observer.ConcreteObserver;
import com.newadmin.demoservice.DesignPattern.observer.ConcreteSubject;
import com.newadmin.demoservice.DesignPattern.observer.Observer;
import com.newadmin.demoservice.DesignPattern.singletonPattern.SingletonPattern;
import com.newadmin.demoservice.DesignPattern.singletonPattern.cache.Cache;
import com.newadmin.demoservice.DesignPattern.singletonPattern.cache.SimpleCache;
import com.newadmin.demoservice.DesignPattern.strategy.AlipayPaymentStrategy;
import com.newadmin.demoservice.DesignPattern.strategy.CreditCardPaymentStrategy;
import com.newadmin.demoservice.DesignPattern.strategy.PaymentContext;

public class run {

    public static void main(String[] args) {

        // 创建普通咖啡对象
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println(
            "成本: " + simpleCoffee.cost() + "; 描述: " + simpleCoffee.getDescription());

        // 加牛奶的咖啡
        Coffee milkCoffee = new MilkDecorator(simpleCoffee);
        System.out.println("成本: " + milkCoffee.cost() + "; 描述: " + milkCoffee.getDescription());

        // 加牛奶和糖的咖啡
        Coffee milkAndSugarCoffee = new SugarDecorator(milkCoffee);
        System.out.println("成本: " + milkAndSugarCoffee.cost() + "; 描述: "
            + milkAndSugarCoffee.getDescription());

        //*******************************************

        // 创建上下文对象
        PaymentContext context = new PaymentContext();

        // 选择信用卡支付策略
        context.setPaymentStrategy(
            new CreditCardPaymentStrategy("1234 5678 9012 3456", "123", "12/23"));
        context.performPayment(100.00);

        // 选择支付宝支付策略
        context.setPaymentStrategy(new AlipayPaymentStrategy("example@alipay.com"));
        context.performPayment(50.00);

        //********************************************

        // 创建具体主题对象
        ConcreteSubject subject = new ConcreteSubject();

        // 创建具体观察者对象，并注册到主题中
        Observer observer1 = new ConcreteObserver("工具人1");
        Observer observer2 = new ConcreteObserver("工具人2");
        subject.registerObserver(observer1);
        subject.registerObserver(observer2);

        // 主题状态变化，触发通知
        subject.setMessage("Hello, observers!666666666");

        //***********************************
        // 创建现有类的实例
        Adaptee adaptee = new Adaptee();
        // 创建适配器，传入现有类的实例
        Target adapter = new Adapter(adaptee);
        // 调用目标接口的方法，实际上是调用了现有类的特定方法
        adapter.request();

        // 使用第一个工厂创建产品A
        Factory factoryA = new ConcreteFactoryA();
        Product productA = factoryA.createProduct();
        productA.use();

        // 使用第二个工厂创建产品B
        Factory factoryB = new ConcreteFactoryB();
        Product productB = factoryB.createProduct();
        productB.use();

        //
        SingletonPattern instance = SingletonPattern.getInstance();
        instance.showMessage();

// 获取缓存实例
        Cache<String, String> cache = SimpleCache.getInstance();

        // 添加数据到缓存，TTL为5秒
        cache.put("user1", "John Doe", 5000);
        cache.put("user2", "Jane Smith", 10000);

        // 从缓存中获取数据
        System.out.println("User1: " + cache.get("user1"));
        System.out.println("User2: " + cache.get("user2"));

        // 显示缓存大小
        System.out.println("Cache size: " + cache.size());

        // 等待6秒后再获取数据
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 再次获取数据
        System.out.println("User1 (after 6 seconds): " + cache.get("user1"));
        System.out.println("User2 (after 6 seconds): " + cache.get("user2"));

        // 显示缓存大小
        System.out.println("过期后的缓存大小: " + cache.size());

        // 清空缓存
        cache.clear();
        System.out.println("清除后的缓存大小: " + cache.size());
    }

}
