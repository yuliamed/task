package by.iba.dto.req.order;

import by.iba.dto.page.PagingCriteriaReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderSearchCriteriaReq extends PagingCriteriaReq {
    private Integer mileage;
    private Integer minYear;
}
