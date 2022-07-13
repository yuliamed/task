package by.iba.entity.report;

import by.iba.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "engine_report")
@Getter
@Setter
public class EngineReport extends AbstractEntity {
    @Column(name = "oil_pure")
    private String oilPure;
    @OneToMany
    private Set<NoteOnWork> noteOnWorkSet;
}