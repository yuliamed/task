package by.iba.security.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String email;
    private String pass;
}
