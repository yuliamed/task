package by.iba.entity.report;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pendant_report")
@Getter
@Setter
public class PendantReport extends AbstractPartReport {
}