package by.iba.dto.req.order;

import by.iba.dto.req.AbstractReq;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public abstract class OrderReq extends AbstractReq {
    private Long autoPickerId;

    @Pattern(regexp = ".{0,512}", message = "AdditionalInfo can`t be bigger then 512 symbols")
    private String additionalInfo;
}
