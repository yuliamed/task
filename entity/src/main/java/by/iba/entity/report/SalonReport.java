package by.iba.entity.report;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "salon_report")
@Getter
@Setter
public class SalonReport extends AbstractPartReport {
}