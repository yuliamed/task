package by.iba.dto.req.user;

import by.iba.dto.AbstractDTO;
import by.iba.entity.enam.RoleEnum;
import by.iba.exception.validation.EnumPattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class RoleReq extends AbstractDTO {
    @EnumPattern(enumClass = RoleEnum.class)
    @NotNull(message = "type of role can`t be empty")
    private String typeOfRole;
}
