package by.iba.dto.req.report;

import by.iba.dto.req.AbstractReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DateReq extends AbstractReq {
    // todo check the name
    private String date;
}
