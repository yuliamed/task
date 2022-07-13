package by.iba.entity.report;

import by.iba.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractPartReport extends AbstractEntity {
    @Column(name = "mark")
    private Byte mark;

    @Column(name="general_comment")
    private String generalComment;

    @Column(name="general_recommendation")
    private String generalRecommendation;

    @OneToMany
    private Set<CarPartDescription> descriptions;

}
