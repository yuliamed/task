package by.iba.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@NoArgsConstructor
public class UserBanDTO extends AbstractDTO{
    @NotBlank
    Boolean isBanned;
}
