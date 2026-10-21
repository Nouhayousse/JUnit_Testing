package TestingLab.order;

public interface NotificationService {
    void sendConfirmation(String customerEmail, String orderId);
}
