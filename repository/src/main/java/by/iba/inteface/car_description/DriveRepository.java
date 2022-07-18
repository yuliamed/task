package by.iba.inteface.car_description;

import by.iba.entity.order.Drive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriveRepository extends JpaRepository<Drive, Long> {
    Optional<Drive> findByName(String name);
}
