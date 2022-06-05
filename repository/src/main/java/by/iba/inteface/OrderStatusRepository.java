package by.iba.inteface;

import by.iba.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
    OrderStatus getByName(String newOrderStatus);

    Optional<OrderStatus> findByName(String status);
}
