package by.iba.entity.report;

import by.iba.entity.TrackingAbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "reports")
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Report extends TrackingAbstractEntity {
}