package by.iba.dto.req.report;

import by.iba.dto.req.AbstractReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class NoteOnWorkReq extends AbstractReq {
    @NotBlank
    @Size(max = 255)
    private String name;
    @Size(max = 255)
    private String description;
}
