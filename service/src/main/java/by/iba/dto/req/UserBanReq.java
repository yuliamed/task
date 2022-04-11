package by.iba.dto.req;

import by.iba.dto.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@NoArgsConstructor
public class UserBanReq extends AbstractDTO {
    @NotBlank
    private Boolean isBanned;
}
