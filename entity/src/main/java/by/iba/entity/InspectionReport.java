package by.iba.entity;

import by.iba.entity.TrackingAbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "inspection_report")
public class InspectionReport extends TrackingAbstractEntity {
}