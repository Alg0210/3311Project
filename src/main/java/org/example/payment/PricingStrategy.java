package org.example.payment;

public interface PricingStrategy {

    double getHourlyRate();
    double calculateDeposit();
    double calculateTotal(int hours);
}
