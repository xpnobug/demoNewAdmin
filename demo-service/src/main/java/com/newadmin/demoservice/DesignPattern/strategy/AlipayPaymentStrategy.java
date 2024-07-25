package com.newadmin.demoservice.DesignPattern.strategy;

// 具体支付策略类：支付宝支付
public class AlipayPaymentStrategy implements PaymentStrategy {

    private String account;

    // 构造函数，传入支付宝账号
    public AlipayPaymentStrategy(String account) {
        this.account = account;
    }

    // 实现支付方法
    @Override
    public void pay(double amount) {
        System.out.println("支付 " + amount + " 支付宝账户美元 " + account);
        // 其他支付逻辑
    }
}
