package by.iba.dto.resp;

import by.iba.dto.req.order.CostReq;
import by.iba.entity.*;
import by.iba.entity.enam.TypeOfCurrency;
import by.iba.exception.validation.EnumPattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class OrderResp extends AbstractResp {
    private Long id;

    private Integer minYear;

    private Integer mileage;//default in km

    private Double minEngineVolume;//default in km

    private Double maxEngineVolume;//default in km

    private Double costValue;

    private CurrencyType currencyType;

    private Set<Drive> drives = new HashSet<>();

    private Set<Transmission> transmissions = new HashSet<>();

    private Set<Engine> engines = new HashSet<>();

    private Set<CarBrand> brands = new HashSet<>();

    private String additionalInfo;

    private LocalDateTime lastUpdateDate;

    private LocalDateTime createdDate;

    private UserResp autoPicker;

    private UserResp creator;

    private OrderStatus status;

}
