package com.newadmin.demoservice.DesignPattern.strategy;

// 上下文类
public class PaymentContext {

    private PaymentStrategy strategy;

    // 设置具体支付策略
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    // 执行支付
    public void performPayment(double amount) {
        strategy.pay(amount);
    }
}
