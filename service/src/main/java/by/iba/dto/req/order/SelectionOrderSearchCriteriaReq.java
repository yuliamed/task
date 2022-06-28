package by.iba.dto.req.order;

import by.iba.dto.page.PagingCriteriaReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SelectionOrderSearchCriteriaReq extends PagingCriteriaReq {
    private Integer mileage;
    private Integer minYear = 1900;
    private Double minEngineVolume;
    private Double maxEngineVolume;
    private String orderStatus;
    private List<String> brands;
    private String engine;
    private String body;
    private Double rangeFrom = 10.0;
    private Double rangeTo = 1000000.0;
    private String currencyType = "USD";
    private String creatorName;
}
