package by.iba.inteface.car_description;

import by.iba.entity.order.Body;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BodyRepository extends JpaRepository<Body, Long> {
    Optional<Body> findByName(String body);
}
