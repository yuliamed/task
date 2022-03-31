package by.iba.entity;

import java.util.List;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name="role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AbstractEntity{

    @Column(name="name", length = 128)
    private String name;
    @Transient
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

}
