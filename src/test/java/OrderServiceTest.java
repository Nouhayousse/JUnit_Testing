import TestingLab.order.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    PaymentGateway paymentGateway;
    @Mock
    OrderRepository orderRepository;
    @Mock
    NotificationService notificationService;

    private OrderService orderService;

    @Captor
    ArgumentCaptor<Order> captor;

    // On construit OrderService nous-mêmes avec le constructeur :
    //   c'est plus clair que @InjectMocks
    //   (qui existe, mais cache le câblage et peut échouer silencieusement).
    @BeforeEach
    void setUp(){
        orderService=new OrderService(paymentGateway,orderRepository,notificationService);
    }


    @Test
    void shouldMarkOrderAsPaidWhenPaymentSucceeds(){
        when(paymentGateway.charge("sara@gmail.com",100.0)).thenReturn(true);
        Order order=orderService.placeOder("sara@gmail.com",100.0);

        assertThat(order.getStatus()).isEqualTo(OrderStatus.PAID);

    }

    @Test
    void shouldSaveOrderAndNotifywhenPaymentSucceeds(){

        // when(paymentGateway.charge("sara@gmail.com",anyDouble())).thenReturn(true);
        // org.mockito.exceptions.misusing.InvalidUseOfMatchersException:
        //Invalid use of argument matchers!
        when(paymentGateway.charge("sara@gmail.com",100.0)).thenReturn(true);

        Order order=orderService.placeOder("sara@gmail.com",100.0);
        verify(orderRepository).save(order);
        verify(notificationService).sendConfirmation("sara@gmail.com",order.getId());


    }





    @Test
    void shouldRejectOrderAndNotSaveWhenPaymentIsRefused(){
        when(paymentGateway.charge("sara@gmail.com",100.0)).thenReturn(false);

        assertThatThrownBy(()->orderService.placeOder("sara@gmail.com",100.0)).isInstanceOf(PaymentRejectedException.class).hasMessageContaining("sara@gmail.com");
        verifyNoInteractions(orderRepository,notificationService);
    }

    @Test
    void shouldNeverChargeWhenAmountIsNotPositive(){
        // Unnecessary stubbings detected.
        // when(paymentGateway.charge("sara@gmail.com",10.0)).thenReturn(true);
        assertThatThrownBy(()->orderService.placeOder("sara@gmail.com",0)).isInstanceOf(IllegalArgumentException.class);
        verifyNoInteractions(paymentGateway,orderRepository,notificationService);
    }



    //Captor with argument captor
    @Test
    void shouldSaveOrderWithPaidStatus(){
        when(paymentGateway.charge("sara@gmail.com",100.0)).thenReturn(true);
        orderService.placeOder("sara@gmail.com",100.0);
        verify(orderRepository).save(captor.capture());
        Order savedOrder=captor.getValue();
        assertThat(savedOrder.getStatus()).isEqualTo(OrderStatus.PAID);
        assertThat(savedOrder.getCustomerEmail()).isEqualTo("sara@gmail.com");
        assertThat(savedOrder.getAmount()).isEqualTo(100.0);

    }


    @Test
    void shouldChargeCustomerWithExactAmount(){
        when(paymentGateway.charge("sara@gmail.com",188.9)).thenReturn(true);
        Order order=orderService.placeOder("sara@gmail.com",188.9);
        verify(orderRepository).save(order);
        assertThat(order.getAmount()).isEqualTo(188.9);
        assertThat(order.getCustomerEmail()).contains("sara");


    }



    @Test
    void shouldChargeBeforeSavingOrder(){
        when(paymentGateway.charge("sara@gmail.com",188.9)).thenReturn(true);
        Order order=orderService.placeOder("sara@gmail.com",188.9);

        InOrder inOrder=inOrder(paymentGateway,orderRepository);
        inOrder.verify(paymentGateway).charge("sara@gmail.com",188.9);
        inOrder.verify(orderRepository).save(order);
    }


    @ParameterizedTest
    @ValueSource(doubles = {0, -1, -100.5})
    void shouldRejectNonPositiveAmounts(double amount) {
        assertThatThrownBy(() -> orderService.placeOder("sara@mail.com", amount))
                .isInstanceOf(IllegalArgumentException.class);

        verifyNoInteractions(paymentGateway);
    }




}
