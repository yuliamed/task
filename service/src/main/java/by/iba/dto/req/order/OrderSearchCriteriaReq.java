package by.iba.dto.req.order;

import by.iba.dto.page.PagingCriteriaReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderSearchCriteriaReq extends PagingCriteriaReq {
    String param;
}
