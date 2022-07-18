package by.iba.dto.req.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class InspectionOrderReq extends OrderReq {
    @NotBlank
    private String autoUrl;
}
