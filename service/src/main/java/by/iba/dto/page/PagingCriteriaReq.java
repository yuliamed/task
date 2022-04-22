package by.iba.dto.page;

import by.iba.dto.req.AbstractReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@NoArgsConstructor
@Getter
@Setter
public class PagingCriteriaReq extends AbstractReq {
    @Min(value = 0, message = "Min number of page is 0")
    private Integer pageNumber = 0;

    @Positive(message = "Page Size must be positive")
    @Min(value = 1, message = "Min size of page is 1")
    private Integer pageSize = 20;

}
