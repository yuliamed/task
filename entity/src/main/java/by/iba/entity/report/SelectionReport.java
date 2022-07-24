package by.iba.entity.report;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "selection_report")
public class SelectionReport extends Report {
    @OneToMany(fetch = FetchType.EAGER)
    private Set<SelectedCar> selectedCarSet = new HashSet<>();
}