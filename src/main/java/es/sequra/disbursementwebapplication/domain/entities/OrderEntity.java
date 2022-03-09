package es.sequra.disbursementwebapplication.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order")
@SQLDelete(sql = "UPDATE order SET deleted = true WHERE id = ?")
@Where(clause = BaseEntity.WHERE_DELETED_CLAUSE)
@SequenceGenerator(name="base_entity_gen", allocationSize = 1, sequenceName = "order_seq")
public class OrderEntity extends BaseEntity {

    private Long orderId;
    private Long merchantId;
    private Long shopperId;
    private BigDecimal amount;
    private Instant completedAt;


}
