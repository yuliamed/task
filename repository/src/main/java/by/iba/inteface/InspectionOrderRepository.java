package by.iba.inteface;

import by.iba.entity.order.InspectionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InspectionOrderRepository extends JpaRepository<InspectionOrder, Long>, JpaSpecificationExecutor<InspectionOrder> {
}
