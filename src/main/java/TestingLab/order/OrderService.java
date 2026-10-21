package TestingLab.order;

import java.util.UUID;

public class OrderService {
    private final PaymentGateway paymentGateway;
    private final OrderRepository orderRepository;
    private final NotificationService notificationService;

    public OrderService(PaymentGateway paymentGateway, OrderRepository orderRepository, NotificationService notificationService) {
        this.paymentGateway = paymentGateway;
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
    }


    public Order placeOder(String customerEmail, double amount) {
        if(amount<=0){
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        // Create a new order with a unique ID
        String orderId = UUID.randomUUID().toString();
        Order order = new Order(orderId, customerEmail, amount);

        if(!paymentGateway.charge(customerEmail, amount)){
            throw new PaymentRejectedException("Payment was rejected for customer: " + customerEmail);
        }

        orderRepository.save(order);
        order.markPaid();
        notificationService.sendConfirmation(customerEmail, orderId);
        return order;
    }
}
