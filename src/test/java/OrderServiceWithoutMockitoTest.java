
import TestingLab.order.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

class OrderServiceWithoutMockitoTest {


    @Test
    void shouldSaveAndConfirmOrderWhenPaymentSucceeds(){
        PaymentGateway acceptingGateway=(email, amount)->true ;  // it's a stub here ! lambda
        InMemoryOrderRepository repository=new InMemoryOrderRepository();

        List<String> senTo=new ArrayList<>();

        NotificationService recorder=(email, orderId)->senTo.add(email); // ici c'est un spy !

        OrderService service=new OrderService(acceptingGateway,repository,recorder);

        Order order=service.placeOder("sara@gmail.com",100.0);

        assertThat(order.getStatus()).isEqualTo(OrderStatus.PAID);
        assertThat(repository.findById(order.getId())).isPresent();
        assertThat(senTo).containsExactly("sara@gmail.com");

    }


    @Test
    void shouldNotSaveOrderWhenPaymentIsRefused() {
        PaymentGateway refusingGateway = (email, amount) -> false;         // stub
        InMemoryOrderRepository repository = new InMemoryOrderRepository();
        NotificationService unusedNotifier = (email, orderId) -> { };      // dummy

        OrderService service = new OrderService(refusingGateway, repository, unusedNotifier);

        assertThatThrownBy(() -> service.placeOder("sara@mail.com", 100.0))
                .isInstanceOf(PaymentRejectedException.class);
    }

}
