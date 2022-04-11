package by.iba.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResetPassReq {
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z][A-Z])(?=.*[!@#&()\"[{}]:;',?/*~$^+=<>]).{6,20}$",
            message = "The pass must contain digits, letters and special characters like !, @, #, &, (, ), \", [{}]," +
                    " :, ;, ', ,, ?, /, *, ~, $, ^, +, =, <, >. " +
                    "Min length of pass is 6, max length is 20")
    @NotBlank(message = "User pass can`t be empty")
    private String pass;
}
