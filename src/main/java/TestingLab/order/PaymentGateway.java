package TestingLab.order;

public interface PaymentGateway {
    boolean charge(String customerEmail,double amount);
}
