package by.iba.entity.order;

import by.iba.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="order_statuses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus extends AbstractEntity {

    @Column(name="name", length = 64, unique = true)
    private String name;

}
