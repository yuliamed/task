package by.iba.entity.report;

import by.iba.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractPartReport extends AbstractEntity {
    @Column(name = "mark")
    private Byte mark;

    @Column(name = "general_comment", length = 1024)
    private String generalComment;

    @Column(name = "general_recommendation", length = 1024)
    private String generalRecommendation;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CarPartDescription> descriptions = new HashSet<>();

}
