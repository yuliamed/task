package by.iba.entity.report;

import by.iba.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "engine_report")
@Getter
@Setter
public class EngineReport extends AbstractEntity {
    @Column(name = "oil_pure", length = 32)
    private String oilPure;
    @OneToMany(cascade = CascadeType.ALL)
    private Set<NoteOnWork> noteOnWorkSet;
}