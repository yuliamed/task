package by.iba.entity.order;

import by.iba.entity.report.SelectionReport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "selection_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SelectionOrder extends Order {
    @Column(name = "min_year")
    private Integer minYear = 1900;

    @Column(name = "range_from")
    private Double rangeFrom;

    @Column(name = "range_to")
    private Double rangeTo;

    @Column(name = "currency_range_from")
    private Double currencyRangeFrom;

    @Column(name = "currency_range_to")
    private Double currencyRangeTo;

    public void abstractEntityPreUpdate() {
        super.abstractEntityPreUpdate();
        currencyRangeTo = rangeTo / currencyType.getRateRelativeDollar();
        currencyRangeFrom = rangeFrom / currencyType.getRateRelativeDollar();
    }

    @ManyToOne
    @JoinColumn(name = "currency_type_id")
    private CurrencyType currencyType;

    @Column(name = "mileage")
    private Integer mileage;//default in km

    @Column(name = "min_engine_volume")
    private Double minEngineVolume;//default in km

    @Column(name = "max_engine_volume")
    private Double maxEngineVolume;//default in km

    @Column(name = "additional_info", length = 512)
    private String additionalInfo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "selec_orders_drives",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "drive_id", referencedColumnName = "id")})
    private Set<Drive> drives = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "selec_orders_transmissions",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "transmission_id", referencedColumnName = "id")})
    private Set<Transmission> transmissions = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "selec_orders_engines",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "engine_id", referencedColumnName = "id")})
    private Set<Engine> engines = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "selec_orders_car_brands",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "brand_id", referencedColumnName = "id")})
    private Set<CarBrand> brands = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "selec_orders_body",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "body_id", referencedColumnName = "id")})
    private Set<Body> bodies = new HashSet<>();
}
