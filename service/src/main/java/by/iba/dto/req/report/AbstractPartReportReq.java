package by.iba.dto.req.report;

import by.iba.dto.req.AbstractReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractPartReportReq extends AbstractReq {

    @NotNull
    @DecimalMin(value = "0")
    @DecimalMax(value = "10")
    private Byte mark;
    @NotBlank
    @Size(max = 1024)
    private String generalComment;
    @NotBlank
    @Size(max = 1024)
    private String generalRecommendation;
    private Set<@Valid CarPartDescriptionReq> descriptions;
}
