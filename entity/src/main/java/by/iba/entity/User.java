package by.iba.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends TrackingAbstractEntity {

    @Column(name = "email", length = 128, unique = true)
    private String email;
    @Column(name = "name", length = 64)
    private String name;
    @Column(name = "surname", length = 64)
    private String surname;
    @Column(name = "pass", length = 256)
    private String pass;
    @Column(name = "ban_date")
    private LocalDateTime banDate;
    @Column(name = "is_active")
    private Boolean isActive = false;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "last_visited_date")
    private LocalDateTime lastVisitDate;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();
}
