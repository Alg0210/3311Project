package org.example.payment;

public class UserPricingStrategy implements PricingStrategy{
    private double hourlyRate;

    public UserPricingStrategy(String userType) {
        switch (userType.toUpperCase()) {
            case "STUDENT":     hourlyRate = 10.0; break;
            case "FACULTY":     hourlyRate = 15.0; break;
            case "RESEARCHER":  hourlyRate = 20.0; break;
            case "GUEST":       hourlyRate = 30.0; break;
            default:            hourlyRate = 0.0;  break;
        }
    }

    @Override
    public double getHourlyRate() {
        return hourlyRate;
    }

    @Override
    public double calculateDeposit() {
        return hourlyRate; // one hour's fee as deposit per Req4
    }

    @Override
    public double calculateTotal(int hours) {
        return hourlyRate * hours;
    }
}
