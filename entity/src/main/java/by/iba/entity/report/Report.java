package by.iba.entity.report;

import by.iba.entity.TrackingAbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Report extends TrackingAbstractEntity {

}