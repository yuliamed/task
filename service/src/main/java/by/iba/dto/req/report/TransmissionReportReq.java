package by.iba.dto.req.report;

import by.iba.dto.req.AbstractReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TransmissionReportReq extends AbstractReq {
    private Set<@Valid NoteOnWorkReq> noteOnWorkSet = new HashSet<>();
}
