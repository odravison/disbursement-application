package es.sequra.disbursementwebapplication.infrastructure.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import es.sequra.disbursementwebapplication.utils.InstantDeserializer;
import es.sequra.disbursementwebapplication.utils.InstantSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order_entity")
@SQLDelete(sql = "UPDATE order_entity SET deleted = true WHERE id = ?")
@Where(clause = BaseEntity.WHERE_DELETED_CLAUSE)
@SequenceGenerator(name="base_entity_gen", allocationSize = 1, sequenceName = "order_entity_seq")
public class OrderEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private MerchantEntity merchant;

    @ManyToOne
    @JoinColumn(name = "shopper_id")
    private ShopperEntity shopper;

    @Column(name = "amount")
    private BigDecimal amount;

    @JsonDeserialize(using = InstantDeserializer.class)
    @JsonSerialize(using = InstantSerializer.class)
    @Column(name = "completed_at")
    private Instant completedAt;


}
