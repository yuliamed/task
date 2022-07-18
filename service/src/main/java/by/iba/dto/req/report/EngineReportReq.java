package by.iba.dto.req.report;

import by.iba.dto.req.AbstractReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class EngineReportReq extends AbstractReq {
    @NotBlank
    @Size(max = 32)
    private String oilPure;

    private Set<@Valid NoteOnWorkReq> noteOnWorkSet = new HashSet<>();
}
