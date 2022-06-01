package by.iba.dto.resp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserResp extends AbstractResp {
    private Long id;
    private String name;
    private String email;
    private String surname;
    private Set<RoleResp> roles = new HashSet<>();
    private LocalDateTime lastVisitDate;
    private String imageUrl;
    private Boolean isActive;
    private LocalDateTime banDate;
    private String activationToken;
}
