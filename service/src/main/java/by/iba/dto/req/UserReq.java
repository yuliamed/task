package by.iba.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class UserReq extends AbstractReq {
    @NotBlank(message = "User name can`t be empty")
    private String name;
    @NotBlank(message = "Email can`t be empty")
    @Pattern(regexp = "^[a-z](\\.?\\w)*@[a-z]+(\\.[a-z]+)+", message = "The email must start with a letter," +
            " all letters are small," +
            " there may be a dot in it," +
            " but not 2 in a row." +
            " The @ must be present and the domain after it")
    private String email;
    @NotBlank(message = "User surname can`t be empty")
    private String surname;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z][A-Z])(?=.*[!@#&()\"[{}]:;',?/*~$^+=<>]).{6,20}$",
            message = "The pass must contain digits, letters and special characters like !, @, #, &, (, ), \", [{}]," +
                    " :, ;, ', ,, ?, /, *, ~, $, ^, +, =, <, >. " +
                    "Min length of pass is 6, max length is 20")
    @NotBlank(message = "User pass can`t be empty")
    private String pass;
    @NotBlank(message = "ImageUrl can`t be empty")
    private String imageUrl;
}
