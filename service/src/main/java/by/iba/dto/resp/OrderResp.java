package by.iba.dto.resp;

import by.iba.entity.order.*;
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
    // Common info
    private Long id;

    private LocalDateTime lastUpdateDate;

    private LocalDateTime createdDate;

    private UserResp autoPicker;

    private UserResp creator;

    private OrderStatus status;

    // Selection info
    private Integer minYear;

    private Integer mileage;//default in km

    private Double minEngineVolume;//default in km

    private Double maxEngineVolume;//default in km

    private Double costValue;

    private Double rangeFrom;

    private Double rangeTo;

    private CurrencyType currencyType;

    private Set<Drive> drives = new HashSet<>();

    private Set<Transmission> transmissions = new HashSet<>();

    private Set<Engine> engines = new HashSet<>();

    private Set<CarBrand> brands = new HashSet<>();

    private Set<Body> bodies = new HashSet<>();

    private String additionalInfo;

    // Inspection Info

}
