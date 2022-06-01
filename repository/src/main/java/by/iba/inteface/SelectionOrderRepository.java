package by.iba.inteface;

import by.iba.entity.SelectionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectionOrderRepository extends JpaRepository<SelectionOrder, Long> {
}
