package by.iba.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="selection_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SelectionOrder extends AbstractEntity{
    @Column(name="min_year")
    private Integer minYear;

    @Column(name="max_price")
    private Integer maxPrice;//default in $

    @Column(name="mileage")
    private Integer mileage;//default in km

    @Column(name="min_engine_volume")
    private Integer minEngineVolume;//default in km

    @Column(name="max_engine_volume")
    private Integer maxEngineVolume;//default in km

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
}
