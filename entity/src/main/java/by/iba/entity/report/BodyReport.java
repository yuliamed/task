package by.iba.entity.report;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "body_report")
@Getter
@Setter
public class BodyReport extends AbstractPartReport {
}