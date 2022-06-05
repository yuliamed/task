package by.iba.dto.req.order;

import by.iba.dto.page.PagingCriteriaReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderSearchCriteriaReq extends PagingCriteriaReq {
    private Integer mileage;
    private Integer minYear = 1900;
    private Double minEngineVolume;
    private Double maxEngineVolume;
    private String orderStatus;
    private List<String> brands;
}
