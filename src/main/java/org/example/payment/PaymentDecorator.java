package org.example.payment;

public class PaymentDecorator {
    private Payment payment;
    private String paymentMethod; // "CREDIT", "DEBIT", "INSTITUTIONAL", "GRANT"
    private String accountReference; // card number, grant ID, institutional account ID

    public PaymentDecorator(Payment payment, String paymentMethod, String accountReference) {
        this.payment = payment;
        this.paymentMethod = paymentMethod;
        this.accountReference = accountReference;
    }

    // validate based on payment method type
    public boolean validate() {
        switch (paymentMethod.toUpperCase()) {
            case "CREDIT":
            case "DEBIT":
                return accountReference != null && accountReference.length() == 16;
            case "INSTITUTIONAL":
                return accountReference != null && accountReference.startsWith("INST-");
            case "GRANT":
                return accountReference != null && accountReference.startsWith("GRT-");
            default:
                return false;
        }
    }

    public Payment process() {
        if (!validate()) {
            System.out.println("Payment validation failed for method: " + paymentMethod);
            return null;
        }
        System.out.println("Processing " + paymentMethod + " payment of $" + payment.getAmount());
        return payment;
    }

    public Payment getPayment() { return payment; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getAccountReference() { return accountReference; }
}
