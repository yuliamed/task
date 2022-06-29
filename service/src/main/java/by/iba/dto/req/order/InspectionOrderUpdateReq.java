package by.iba.dto.req.order;

import by.iba.dto.req.AbstractReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class InspectionOrderUpdateReq extends AbstractReq {
    @NotBlank
    private String autoUrl;
}
