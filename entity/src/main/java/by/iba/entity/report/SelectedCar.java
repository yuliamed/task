package by.iba.entity.report;

import by.iba.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "selected_car")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SelectedCar extends AbstractEntity {
    @Column(name = "car_url")
    private String carUrl;
    @Column(name = "comment", length = 1024)
    private String comment;
//    @Column(name = "date_joint_inspection")
//    private LocalDateTime dateJointInspection;
    // одобрить осмотр автомобиля?
}