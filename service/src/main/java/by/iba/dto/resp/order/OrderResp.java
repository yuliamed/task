package by.iba.dto.resp.order;

import by.iba.entity.order.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class OrderResp extends AbstractOrderResp {
    //InspectionOrder
    private String autoUrl;

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
}
