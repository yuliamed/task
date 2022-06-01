package by.iba.dto.req.order;

import by.iba.entity.CurrencyType;
import by.iba.entity.enam.TypeOfCurrency;
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
    @EnumPattern(enumClass = TypeOfCurrency.class, message = "Input correct name of currency type")
    private String type;
}
