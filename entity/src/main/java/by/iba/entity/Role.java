package by.iba.entity;

import java.util.List;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="t_role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    private Long id;
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
