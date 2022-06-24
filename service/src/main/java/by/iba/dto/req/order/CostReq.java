package by.iba.dto.req.order;

import by.iba.entity.enam.CurrencyEnum;
import by.iba.exception.validation.EnumPattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CostReq {
    @NotNull
    private Double value;

    @NotNull
    @EnumPattern(enumClass = CurrencyEnum.class, message = "Input correct name of currency type")
    private String type;
}
