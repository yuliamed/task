package by.iba.dto.req.order;

import by.iba.entity.enam.BodyTypeEnum;
import by.iba.exception.validation.EnumPattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class BodyReq {
    @NotNull
    @EnumPattern(enumClass = BodyTypeEnum.class, message = "Input correct name of body type")
    private String name;
}
