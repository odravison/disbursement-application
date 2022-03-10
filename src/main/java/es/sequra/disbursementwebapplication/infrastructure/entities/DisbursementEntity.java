package es.sequra.disbursementwebapplication.infrastructure.entities;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@Entity(name = "disbursement")
@SQLDelete(sql = "UPDATE disbursement SET deleted = true WHERE id = ?")
@Where(clause = BaseEntity.WHERE_DELETED_CLAUSE)
@SequenceGenerator(name="base_entity_gen", allocationSize = 1, sequenceName = "disbursement_seq")
public class DisbursementEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "merchant_id", nullable = false)
    private MerchantEntity merchant;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @Column(name = "disbursed_amount", nullable = false)
    private BigDecimal disbursedAmount;

    @Column(name = "fee_value_charged", nullable = false)
    private BigDecimal feeValueCharged;

    @Column(name = "fee", nullable = false)
    private BigDecimal fee;

    @Column(name = "week_of_year", nullable = false)
    private Integer weekOfYear;

    @Column(name = "year", nullable = false)
    private Integer year;

}
