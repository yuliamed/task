package by.iba.dto.req.user;

import by.iba.dto.req.AbstractReq;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ChangePassDto extends AbstractReq {
    private String token;
    private String newPass;
}
