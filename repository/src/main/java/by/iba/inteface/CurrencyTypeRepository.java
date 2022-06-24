package by.iba.inteface;

import by.iba.entity.order.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CurrencyTypeRepository extends JpaRepository<CurrencyType, Long> {
   Optional<CurrencyType> findByName(String currencyType);
}
