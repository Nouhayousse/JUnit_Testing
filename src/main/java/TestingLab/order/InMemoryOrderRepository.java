package TestingLab.order;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
// c'est un fake !
public class InMemoryOrderRepository implements OrderRepository{
    private final Map<String,Order> orders=new HashMap<>();



    @Override
    public Order save(Order order) {
        orders.put(order.getId(), order);
        return order;
    }

    @Override
    public Optional<Order> findById(String id) {
        return Optional.ofNullable(orders.get(id));
    }
}
