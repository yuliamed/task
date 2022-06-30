package by.iba.dto.req.order;

import by.iba.dto.req.AbstractReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter
@Setter
public class InspectionOrderUpdateReq extends AbstractReq {
    @NotBlank
    private String autoUrl;

    @Pattern(regexp = ".{0,512}", message = "AdditionalInfo can`t be bigger then 512 symbols")
    private String additionalInfo;
}
