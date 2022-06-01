package by.iba.dto.req.order;

import by.iba.dto.req.AbstractReq;
import by.iba.entity.enam.TypeOfDrive;
import by.iba.entity.enam.TypeOfTransmission;
import by.iba.exception.validation.EnumPattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TransmissionReq extends AbstractReq {
    @EnumPattern(enumClass = TypeOfTransmission.class)
    private String name;
}
