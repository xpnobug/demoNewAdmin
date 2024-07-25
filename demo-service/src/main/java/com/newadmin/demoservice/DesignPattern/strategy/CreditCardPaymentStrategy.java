package com.newadmin.demoservice.DesignPattern.strategy;

// 具体支付策略类：信用卡支付
public class CreditCardPaymentStrategy implements PaymentStrategy {

    private String cardNumber;
    private String cvv;
    private String expirationDate;

    // 构造函数，传入信用卡信息
    public CreditCardPaymentStrategy(String cardNumber, String cvv, String expirationDate) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }

    // 实现支付方法
    @Override
    public void pay(double amount) {
        System.out.println("支付 " + amount + " 使用信用卡的美元 " + cardNumber);
        // 其他支付逻辑
    }
}
