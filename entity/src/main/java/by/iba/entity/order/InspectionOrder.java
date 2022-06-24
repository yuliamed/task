package by.iba.entity.order;

import by.iba.entity.InspectionReport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "inspection_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InspectionOrder extends Order {
    @Column(name = "auto_url", nullable = false, length = 255)
    private String autoUrl;

    @OneToOne
    @JoinColumn(name = "inspection_report_id")
    private InspectionReport inspectionReport;
}
