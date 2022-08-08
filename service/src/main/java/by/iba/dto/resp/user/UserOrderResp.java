package by.iba.dto.resp.user;

import by.iba.dto.resp.AbstractResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserOrderResp extends AbstractResp {
    private Long id;
    private String name;
    private String email;
    private String surname;
    private LocalDateTime lastVisitDate;
    private String imageUrl;
}
