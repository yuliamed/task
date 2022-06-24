package by.iba.inteface;

import by.iba.entity.order.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransmissionRepository extends JpaRepository<Transmission, Long> {
    Transmission findByName(String name);
}
