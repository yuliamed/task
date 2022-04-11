package by.iba.dto.req;

import by.iba.dto.AbstractDTO;
import by.iba.entity.TypeOfRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class RoleReq extends AbstractDTO {
    @NotBlank(message = "type of role can`t be empty")
    private TypeOfRole typeOfRole;
}
