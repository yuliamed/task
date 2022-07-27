package by.iba.dto.req.report;

import by.iba.dto.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CarComputerErrorReq extends AbstractDTO {
    @Size(max = 1024)
    private String name;
    @Size(max = 1024)
    private String description;
}

