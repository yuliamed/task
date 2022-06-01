package by.iba.dto.req.order;

import by.iba.dto.req.AbstractReq;
import by.iba.entity.enam.BrandNameEnum;
import by.iba.exception.validation.EnumPattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CarBrandReq extends AbstractReq {
    //TODO ?? а надо ли оно вообще
    @EnumPattern(enumClass = BrandNameEnum.class, message = "Input correct name of car brand")
    private String name;
}
