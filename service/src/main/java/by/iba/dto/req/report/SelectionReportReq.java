package by.iba.dto.req.report;

import by.iba.dto.req.AbstractReq;
import by.iba.dto.resp.report.SelectedCarResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SelectionReportReq extends AbstractReq {
    private Set<SelectedCarReq> selectedCarSet = new HashSet<>();
}
