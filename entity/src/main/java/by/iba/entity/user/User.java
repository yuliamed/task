package by.iba.entity.user;

import by.iba.entity.TrackingAbstractEntity;
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

    @Column(name = "email", length = 128, unique = true, nullable = false)
    private String email;
    @Column(name = "name", length = 64)
    private String name;
    @Column(name = "surname", length = 64)
    private String surname;
    @Column(name = "pass", length = 256, nullable = false)
    private String pass;
    @Column(name = "ban_date")
    private LocalDateTime banDate;
    @Column(name = "is_active")
    private Boolean isActive = false;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "recovery_token")
    private String recoveryToken;
    @Column(name = "activation_token")
    private String activationToken;
    @Column(name = "token_creation_date")
    private LocalDateTime tokenCreationDate;
    @Column(name = "last_visit_date", nullable = false)
    private LocalDateTime lastVisitDate = LocalDateTime.now();
    // todo ADD PHONE_NUMBER
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles = new HashSet<>();

}
