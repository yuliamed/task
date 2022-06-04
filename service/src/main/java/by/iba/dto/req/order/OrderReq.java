package by.iba.dto.req.order;

import by.iba.dto.req.AbstractReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class OrderReq extends AbstractReq {

    @Min(value = 1900)
    @Max(value = 2022)
    private Integer minYear;

    private Integer mileage;//default in km

    private Double minEngineVolume;//default in km

    private Double maxEngineVolume;//default in km

    @Pattern(regexp = ".{0,512}", message = "AdditionalInfo can`t be bigger then 512 symbols")
    private String additionalInfo;

    @Valid
    private CostReq cost;

    private Set<@Valid DriveReq> drives = new HashSet<>();

    private Set<@Valid TransmissionReq> transmissions = new HashSet<>();

    private Set<@Valid EngineReq> engines = new HashSet<>();

    //TODO validation
    private Set<@Valid CarBrandReq> brands = new HashSet<>();

    private Long carPickerId;


}
