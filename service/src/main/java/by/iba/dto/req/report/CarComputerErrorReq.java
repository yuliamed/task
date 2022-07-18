package by.iba.dto.req.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CarComputerErrorReq {
    @Size(max = 1024)
    private String name;
    @Size(max = 1024)
    private String description;
}

