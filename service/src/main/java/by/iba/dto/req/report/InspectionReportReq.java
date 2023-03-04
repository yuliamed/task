package by.iba.dto.req.report;

import by.iba.dto.req.AbstractReq;
import by.iba.dto.req.order.*;
import by.iba.entity.enam.CurrencyEnum;
import by.iba.entity.report.PendantReport;
import by.iba.exception.validation.EnumPattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class InspectionReportReq extends AbstractReq {
    @NotBlank
    private String model;

    @NotNull
    @Min(value = 1900)
    @Max(value = 2022)
    private Integer year;

    @NotNull
    private Double engineVolume;

    @NotNull
    private LocalDate inspectionDate;

    @NotNull
    private Integer mileage;//default in km

    @NotNull
    private Boolean isMileageReal;

    @NotBlank
    private String vinNumber;

    @NotNull
    private Boolean isVinNumberReal;

    @NotNull
    private Double costValue;

    @NotNull
    private Double auctionValue;

//    @NotBlank
//    @EnumPattern(enumClass = CurrencyEnum.class, message = "Input correct name of currency type")
    private @Valid CurrencyTypeReq currencyType;

    private @Valid DriveReq drive;

    private @Valid BodyReq body;

    private @Valid TransmissionReq transmission;

    private @Valid EngineReq engine;

    private @Valid CarBrandReq brand;
    // reports
    private @Valid EngineReportReq engineReport;

    private @Valid TransmissionReportReq transmissionReport;

    private @Valid SalonReportReq salonReport;

    private @Valid BodyReportReq bodyReport;

    private @Valid ElectricalEquipmentReportReq electricalEquipmentReport;

    private @Valid PendantReportReq pendantReport;

    private Set<@Valid CarComputerErrorReq> carComputerErrors = new HashSet<>();
}
