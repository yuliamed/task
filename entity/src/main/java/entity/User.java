package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Accessors(chain = true)
@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "generator")
    private Long id;
    @Column
    private String name;

}
