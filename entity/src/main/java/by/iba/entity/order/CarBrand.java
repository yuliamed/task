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
@Table(name = "car_brands")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarBrand extends AbstractEntity {
    @Column(name = "name", length = 64, unique = true)
    private String name;
}

