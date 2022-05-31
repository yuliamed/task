package by.iba.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="engines")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Engine extends AbstractEntity{

    @Column(name="name", length = 32, unique = true)
    private String name;

}
