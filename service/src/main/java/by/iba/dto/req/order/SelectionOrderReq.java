package by.iba.dto.req.order;

import by.iba.entity.enam.CurrencyEnum;
import by.iba.entity.order.CurrencyType;
import by.iba.exception.validation.EnumPattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class SelectionOrderReq extends OrderReq {
    @NotNull
    @Min(value = 1900)
    @Max(value = 2022)
    private Integer minYear;

    private String model;

    @NotNull
    private Integer mileage;//default in km
    @NotNull
    private Double rangeFrom;
    @NotNull
    private Double rangeTo;

    private Double minEngineVolume;//default in km

    private Double maxEngineVolume;//default in km

    private @Valid CurrencyTypeReq currencyType;

    private @Valid DriveReq drive;

    private @Valid BodyReq body;

    private @Valid TransmissionReq transmissions;

    private Set<@Valid EngineReq> engines = new HashSet<>();

    private @Valid CarBrandReq brand;

}
