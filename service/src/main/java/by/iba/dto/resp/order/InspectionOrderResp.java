package by.iba.dto.resp.order;

import by.iba.dto.resp.report.InspectionReportResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InspectionOrderResp extends AbstractOrderResp {
    private String autoUrl;
}
