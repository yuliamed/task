package by.iba.dto.req.order;

import by.iba.dto.req.AbstractReq;
import by.iba.entity.enam.CurrencyEnum;
import by.iba.exception.validation.EnumPattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class SelectionOrderUpdateReq extends AbstractReq {

    @Min(value = 1900)
    @Max(value = 2022)
    private Integer minYear;

    @NotNull
    private Integer mileage;//default in km

    private Double minEngineVolume;//default in km

    private Double maxEngineVolume;//default in km

    @Pattern(regexp = ".{0,512}", message = "Additional Info can`t be bigger then 512 symbols")
    private String additionalInfo;
    @NotNull
    private Double rangeFrom;
    @NotNull
    private Double rangeTo;

    private @Valid CurrencyTypeReq currencyType;

    private @Valid DriveReq drive;

    private @Valid BodyReq body;

    private @Valid TransmissionReq transmissions;

    private Set<@Valid EngineReq> engines = new HashSet<>();

    private @Valid CarBrandReq brand;

    private String model;

}
