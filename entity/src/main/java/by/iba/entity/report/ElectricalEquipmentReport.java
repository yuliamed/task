package by.iba.entity.report;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "electrical_equipment_report")
@Getter
@Setter
public class ElectricalEquipmentReport extends AbstractPartReport {
}