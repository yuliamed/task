package by.iba.inteface.report;

import by.iba.entity.report.SelectedCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedCarRepository extends JpaRepository<SelectedCar, Long> {
}
