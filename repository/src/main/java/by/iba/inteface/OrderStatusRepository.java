package by.iba.inteface;

import by.iba.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    OrderStatus getByName(String newOrderStatus);
}
