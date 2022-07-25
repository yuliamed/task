package by.iba.entity.order;

import by.iba.entity.TrackingAbstractEntity;
import by.iba.entity.report.Report;
import by.iba.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "creator")
    private User creator;

//    @OneToOne
//    @JoinColumn(name = "report_id")
//    private Report report;
}
