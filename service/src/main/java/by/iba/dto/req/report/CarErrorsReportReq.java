package by.iba.dto.req.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CarErrorsReportReq {
    private Set<CarComputerErrorReq> carComputerErrors = new HashSet<>();
}
