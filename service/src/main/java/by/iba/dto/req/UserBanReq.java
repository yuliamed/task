package by.iba.dto.req;

import by.iba.dto.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserBanReq extends AbstractDTO {
    @NotNull(message = "input status of user")
    private Boolean isBanned;
}
