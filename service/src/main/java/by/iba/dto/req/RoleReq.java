package by.iba.dto.req;

import by.iba.dto.AbstractDTO;
import by.iba.entity.TypeOfRole;
import by.iba.exception.validation.TypeOfRolePattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class RoleReq extends AbstractDTO {
    @TypeOfRolePattern(enumClass = TypeOfRole.class)
    @NotNull(message = "type of role can`t be empty")
    private String typeOfRole;
}
