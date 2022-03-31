package by.iba.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends AbstractEntity {

    @Column(name = "email", length = 128, unique = true)
    private String email;
    @Column(name = "user_name", length = 128)
    private String userName;
    @Column(name = "user_surname", length = 128)
    private String userSurname;
    @Column(name = "pass", length = 256)
    private String pass;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;


}
