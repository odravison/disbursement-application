package es.sequra.disbursementwebapplication.infrastructure.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {

    public static final String WHERE_DELETED_CLAUSE = "deleted = false";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base_entity_gen")
    @Column(name = "id")
    private Long id;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_at")
    private Instant lastModifiedAt;
}
