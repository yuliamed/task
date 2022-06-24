package by.iba.inteface;

import by.iba.entity.order.Drive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriveRepository extends JpaRepository<Drive, Long> {
    Drive findByName(String name);
}
