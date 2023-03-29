package by.iba.dto.req.order;

import by.iba.dto.page.PagingCriteriaReq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class OrderSearchCriteriaReq extends PagingCriteriaReq {
    private String param;
}
