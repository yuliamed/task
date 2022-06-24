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
public class SelectionOrderReq extends AbstractReq {
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

    @Pattern(regexp = ".{0,512}", message = "AdditionalInfo can`t be bigger then 512 symbols")
    private String additionalInfo;

    @NotBlank
    @EnumPattern(enumClass = CurrencyEnum.class, message = "Input correct name of currency type")
    private String currencyType;

    private Set<@Valid DriveReq> drives = new HashSet<>();

    private Set<@Valid BodyReq> bodies = new HashSet<>();

    private Set<@Valid TransmissionReq> transmissions = new HashSet<>();

    private Set<@Valid EngineReq> engines = new HashSet<>();

    //TODO validation
    private Set<@Valid CarBrandReq> brands = new HashSet<>();

    private Long autoPickerId;

}
