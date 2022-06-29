package by.iba.dto.req.order;

import by.iba.dto.req.AbstractReq;
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