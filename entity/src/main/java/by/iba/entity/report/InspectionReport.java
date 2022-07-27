package by.iba.entity.report;

import by.iba.entity.order.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "inspection_report")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InspectionReport extends Report {
    @Column(name = "model")
    private String model;

    @Column(name = "year")
    private Integer year;

    @Column(name = "engine_volume")
    private Double engineVolume;

    @Column(name = "inspection_Date")
    private LocalDate inspectionDate;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "is_mileage_real")
    private Boolean isMileageReal = true;

    @Column(name = "vin_number")
    private String vinNumber;

    @Column(name = "is_vin_number_real")
    private Boolean isVinNumberReal = true;

    @Column(name = "cost_value")
    private Double costValue;

    @Column(name = "auction_value")
    private Double auctionValue; // сумма торга

    @ManyToOne
    @JoinColumn(name = "currency_type_id")
    private CurrencyType currencyType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "drive")
    private Drive drive;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transmission")
    private Transmission transmission;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "engine")
    private Engine engine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_brand")
    private CarBrand brand;

    @ManyToOne
    @JoinColumn(name = "body")
    private Body body;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CarComputerError> carComputerErrors = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "body_report_id")
    private BodyReport bodyReport;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "salon_report_id")
    private SalonReport salonReport;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engine_report_id")
    private EngineReport engineReport;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transmission_report_id")
    private TransmissionReport transmissionReport;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "electrical_equipment_report_id")
    private ElectricalEquipmentReport electricalEquipmentReport;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pendant_report_id")
    private PendantReport pendantReport;
}