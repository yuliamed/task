package by.iba.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class SignUpReq extends AbstractReq {
    @NotBlank(message = "User name can`t be empty")
    private String name;

    @NotBlank
    @Pattern(regexp = "^[a-z](\\.?\\w)*@[a-z]+(\\.[a-z]+)+", message = "The login must start with a letter," +
            " all letters are small," +
            " there may be a dot in it," +
            " but not 2 in a row." +
            " The @ must be present and the domain after it")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z][A-Z])(?=.*[!@#&()\"[{}]:;',?/*~$^+=<>]).{6,20}$",
            message = "The pass must contain digits, letters and special characters like !, @, #, &, (, ), \", [{}]," +
                    " :, ;, ', ,, ?, /, *, ~, $, ^, +, =, <, >. " +
                    "Min length of pass is 6, max length is 20")
    @NotBlank(message = "User pass can`t be empty")
    private String pass;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z][A-Z])(?=.*[!@#&()\"[{}]:;',?/*~$^+=<>]).{6,20}$",
            message = "The pass must contain digits, letters and special characters like !, @, #, &, (, ), \", [{}]," +
                    " :, ;, ', ,, ?, /, *, ~, $, ^, +, =, <, >. " +
                    "Min length of pass is 6, max length is 20")
    @NotBlank(message = "User confirm pass can`t be empty")
    private String confirmPass;
}
