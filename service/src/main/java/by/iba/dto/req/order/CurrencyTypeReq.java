package by.iba.dto.req.order;

import by.iba.entity.enam.CurrencyEnum;
import by.iba.exception.validation.EnumPattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class CurrencyTypeReq {
    @NotBlank
    @EnumPattern(enumClass = CurrencyEnum.class, message = "Input correct name of currency type")
    private String name;
}
