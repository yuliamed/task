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
@Table(name = "note_on_work")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteOnWork extends AbstractEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
