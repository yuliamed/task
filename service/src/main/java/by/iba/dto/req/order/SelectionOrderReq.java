package by.iba.dto.req.order;

import by.iba.entity.enam.CurrencyEnum;
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

    @NotNull
    private Integer mileage;//default in km
    @NotNull
    private Double rangeFrom;
    @NotNull
    private Double rangeTo;

    private Double minEngineVolume;//default in km

    private Double maxEngineVolume;//default in km

    @NotBlank
    @EnumPattern(enumClass = CurrencyEnum.class, message = "Input correct name of currency type")
    private String currencyType;

    private Set<@Valid DriveReq> drives = new HashSet<>();

    private Set<@Valid BodyReq> bodies = new HashSet<>();

    private Set<@Valid TransmissionReq> transmissions = new HashSet<>();

    private Set<@Valid EngineReq> engines = new HashSet<>();

    private Set<@Valid CarBrandReq> brands = new HashSet<>();

}
