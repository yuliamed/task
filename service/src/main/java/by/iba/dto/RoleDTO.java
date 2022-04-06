package by.iba.dto;

import by.iba.entity.TypeOfRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class RoleDTO extends AbstractDTO {
    @NotBlank(message = "type of role can`t be empty")
    private TypeOfRole typeOfRole;
}
