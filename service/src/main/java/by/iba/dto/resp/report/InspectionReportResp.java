package by.iba.dto.resp.report;

import by.iba.dto.resp.AbstractResp;
import by.iba.entity.order.*;
import by.iba.entity.report.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class InspectionReportResp extends AbstractResp {
    private Long id;

    private String model;

    private Integer year;

    private Double engineVolume;

    private LocalDate inspectionDate;

    private LocalDateTime lastUpdateDate;

    private LocalDateTime creationDate;

    private Integer mileage;//default in km

    private Boolean isMileageReal;

    private String vinNumber;

    private Boolean isVinNumberReal;

    private Double costValue;

    private Double auctionValue;

    private String currencyType;

    private Drive drive;

    private Body body;

    private Transmission transmission;

    private Engine engine;

    private CarBrand brand;

    // reports
    private EngineReport engineReport;

    private TransmissionReport transmissionReport;

    private SalonReport salonReport;

    private BodyReport bodyReport;

    private ElectricalEquipmentReport electricalEquipmentReport;

    private PendantReport pendantReport;

    private Set<CarComputerError> carComputerErrors = new HashSet<>();
}
