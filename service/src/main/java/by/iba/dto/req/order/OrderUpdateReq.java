package by.iba.dto.req.order;

import by.iba.dto.req.AbstractReq;
import by.iba.entity.CurrencyType;
import by.iba.entity.enam.TypeOfCurrency;
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
public class OrderUpdateReq extends AbstractReq {

    @Min(value = 1900)
    @Max(value = 2022)
    private Integer minYear;

    private Integer mileage;//default in km

    private Double minEngineVolume;//default in km

    private Double maxEngineVolume;//default in km

    @Pattern(regexp = ".{0,512}", message = "AdditionalInfo can`t be bigger then 512 symbols")
    private String additionalInfo;


    private Double costValue;

    @EnumPattern(enumClass = TypeOfCurrency.class, message = "Input correct name of currency type")
    private String currencyType;

    private Set<@Valid DriveReq> drives = new HashSet<>();

    private Set<@Valid TransmissionReq> transmissions = new HashSet<>();

    private Set<@Valid EngineReq> engines = new HashSet<>();

    private Set<@Valid CarBrandReq> brands = new HashSet<>();

}
