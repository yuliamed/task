package by.iba.entity.report;

import by.iba.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "car_part_description")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarPartDescription extends AbstractEntity {

    @Column(name = "describing_part", length = 32)
    private String describingPart;

    @Column(name = "comment", length = 1024)
    private String comment;

    @Column(name = "photo_url", length = 256)
    private String photoUrl;

    @Column(name = "recommendation", length = 1024)
    private String recommendation;
}
