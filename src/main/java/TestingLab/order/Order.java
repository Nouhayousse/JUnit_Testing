package TestingLab.order;

public class Order {
    private final String id;
    private final String customerEmail;
    private final double amount;
    private OrderStatus status = OrderStatus.PENDING;

    public Order(String id, String customerEmail, double amount) {
        this.id = id;
        this.customerEmail = customerEmail;
        this.amount = amount;
    }

    public String getId(){
        return id;
    }
    public String getCustomerEmail() { return customerEmail; }
    public double getAmount() { return amount; }
    public OrderStatus getStatus() { return status; }

    public void markPaid(){
        this.status=OrderStatus.PAID;
    }

}
