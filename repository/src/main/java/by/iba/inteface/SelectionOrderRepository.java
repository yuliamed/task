package by.iba.inteface;

import by.iba.entity.SelectionOrder;
import by.iba.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectionOrderRepository extends JpaRepository<SelectionOrder, Long>,
        JpaSpecificationExecutor<SelectionOrder> {
}
