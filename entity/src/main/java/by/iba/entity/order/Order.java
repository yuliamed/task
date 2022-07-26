package by.iba.entity.order;

import by.iba.entity.TrackingAbstractEntity;
import by.iba.entity.report.Report;
import by.iba.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Order extends TrackingAbstractEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_status")
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auto_picker")
    private User autoPicker;

    @Column(name = "is_autopicker_selected")
    private Boolean isAutoPickerSelected;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator")
    private User creator;

    @OneToOne
    @JoinColumn(name = "report_id")
    private Report report;

    @PrePersist
    protected void abstractEntityPreInit() {
        super.abstractEntityPreInit();
        this.isAutoPickerSelected = Objects.nonNull(autoPicker);
    }
}
