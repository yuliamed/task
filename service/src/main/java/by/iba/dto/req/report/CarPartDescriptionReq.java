package by.iba.dto.req.report;

import by.iba.dto.req.AbstractReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CarPartDescriptionReq extends AbstractReq {
    @NotBlank
    @Size(max = 32)
    private String describingPart;
    @NotBlank
    @Size(max = 1024)
    private String comment;
    @Size(max = 256)
    private String photoUrl;
    @Size(max = 1024)
    private String recommendation;
}
