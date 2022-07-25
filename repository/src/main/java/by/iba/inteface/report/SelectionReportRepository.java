package by.iba.inteface.report;

import by.iba.entity.report.SelectionReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectionReportRepository extends JpaRepository<SelectionReport, Long> {
}
