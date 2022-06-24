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
@Table(name="bodies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Body extends AbstractEntity {
    @Column(name="name", length = 32, unique = true)
    private String name;
}
