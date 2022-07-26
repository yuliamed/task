package by.iba.dto.resp.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SelectedCarResp {
    private String carUrl;
    private String comment;
    private LocalDate dateJointInspection;
}
