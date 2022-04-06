package by.iba.dto;

import by.iba.dto.req.AbstractReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdate extends AbstractReq {
    @NotBlank(message = "Email can`t be empty")
    @Pattern(regexp = "^[a-z](\\.?\\w)*@[a-z]+(\\.[a-z]+)+", message = "The login must start with a letter," +
            " all letters are small," +
            " there may be a dot in it," +
            " but not 2 in a row." +
            " The @ must be present and the domain after it")
    private String email;
    @NotBlank(message = "User name can`t be empty")
    private String name;
    @NotBlank(message = "User surname can`t be empty")
    private String surname;
    @NotBlank(message = "imageUrl can`t be empty")
    private String imageUrl;
}
