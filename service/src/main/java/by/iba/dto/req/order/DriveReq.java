package by.iba.dto.req.order;

import by.iba.dto.req.AbstractReq;
import by.iba.entity.enam.TypeOfDrive;
import by.iba.exception.validation.EnumPattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DriveReq extends AbstractReq {
    @EnumPattern(enumClass = TypeOfDrive.class)
    private String name;
}
