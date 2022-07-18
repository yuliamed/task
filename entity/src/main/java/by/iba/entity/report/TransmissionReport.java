package by.iba.entity.report;

import by.iba.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "transmission_report")
@Getter
@Setter
public class TransmissionReport extends AbstractEntity {
    @OneToMany(cascade = CascadeType.ALL)
    private Set<NoteOnWork> noteOnWorkSet;
}