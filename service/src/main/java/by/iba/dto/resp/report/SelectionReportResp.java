package by.iba.dto.resp.report;

import by.iba.dto.resp.AbstractResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SelectionReportResp extends AbstractResp {
    private LocalDateTime creationDate;
    private Set<SelectedCarResp> selectedCarSet = new HashSet<>();
}
