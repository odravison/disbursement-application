package es.sequra.disbursementwebapplication.infrastructure.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    public static final String WHERE_DELETED_CLAUSE = "deleted = false";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base_entity_gen")
    @Column(name = "id")
    private Long id;

    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "last_modified_at",  nullable = false)
    private Instant lastModifiedAt;

    @Column(name = "deleted")
    private boolean deleted;
}
