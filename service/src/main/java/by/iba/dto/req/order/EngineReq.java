package by.iba.dto.req.order;

import by.iba.dto.req.AbstractReq;
import by.iba.entity.enam.TypeOfDrive;
import by.iba.entity.enam.TypeOfEngine;
import by.iba.exception.validation.EnumPattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EngineReq extends AbstractReq {
    @EnumPattern(enumClass = TypeOfEngine.class, message = "Input correct name of engine type")
    private String name;
}
