package TestingLab.order;

public class PaymentRejectedException extends RuntimeException{
    public PaymentRejectedException(String msg){
        super(msg);
    }
}
