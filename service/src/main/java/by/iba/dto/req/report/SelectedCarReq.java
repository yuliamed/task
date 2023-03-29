package by.iba.dto.req.report;

import by.iba.dto.req.AbstractReq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SelectedCarReq extends AbstractReq {
    private String carUrl;
    private String comment;

}
