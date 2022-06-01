package by.iba.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cost")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cost extends AbstractEntity {
    @Column(name = "value")
    private Double value;
    @ManyToOne
    @JoinColumn(name = "currency_type_id")
    private CurrencyType type;
}
