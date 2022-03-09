package es.sequra.disbursementwebapplication.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "disbursement")
@SQLDelete(sql = "UPDATE disbursement SET deleted = true WHERE id = ?")
@Where(clause = BaseEntity.WHERE_DELETED_CLAUSE)
@SequenceGenerator(name="base_entity_gen", allocationSize = 1, sequenceName = "disbursement_seq")
public class DisbursementEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private MerchantEntity merchant;

    @OneToMany
    @JoinTable(name = "disbursement_orders",
            joinColumns = @JoinColumn(name = "disbursement_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<OrderEntity> ordersOnWeek;
    private BigDecimal disbursedAmount;
    private BigDecimal fee;
    private Integer weekOfYear;
    private Integer year;

}
