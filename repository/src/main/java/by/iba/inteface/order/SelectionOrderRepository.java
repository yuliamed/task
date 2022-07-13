package by.iba.inteface.order;

import by.iba.entity.order.SelectionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectionOrderRepository extends JpaRepository<SelectionOrder, Long>,
        JpaSpecificationExecutor<SelectionOrder> {
}
