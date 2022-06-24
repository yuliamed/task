package by.iba.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@MappedSuperclass
@NoArgsConstructor
public abstract class TrackingAbstractEntity extends AbstractEntity {
    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;
    @Column(name = "last_update_date", nullable = false)
    private LocalDateTime lastUpdateDate;
    @Column(name = "version", nullable = false)
    private Integer version;


    @PrePersist
    private void abstractEntityPreInit() {
        this.version = 1;
        this.creationDate = LocalDateTime.now();
        this.lastUpdateDate = LocalDateTime.now();

    }

    @PreUpdate
    protected void abstractEntityPreUpdate() {
        if (Objects.nonNull(this.version)){
            this.version++;
        }
        this.lastUpdateDate = LocalDateTime.now();
    }


}
