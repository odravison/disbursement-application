package es.sequra.disbursementwebapplication.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.RoundingMode.HALF_EVEN;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "configuration_fee")
@SequenceGenerator(name="base_entity_gen", allocationSize = 1, sequenceName = "configuration_fee_seq")
public class ConfigurationFee extends BaseEntity {

    public static BigDecimal LOWEST_FEE_VALUE = new BigDecimal(0.85)
            .setScale(2, HALF_EVEN);
    private static double ONE_HUNDRED = 100.00;

    @Column(name = "range_floor_amount")
    private BigDecimal rangeFloorAmount;

    @Column(name = "range_ceil_amount")
    private BigDecimal rangeCeilAmount;

    @Column(name = "fee")
    private BigDecimal fee;

    public BigDecimal getFeeValue(BigDecimal amount) {
        BigDecimal value = amount.multiply(this.fee).divide(BigDecimal.valueOf(ONE_HUNDRED), 2, HALF_EVEN);
        return value;
    }

    public static BigDecimal getFeeValue(BigDecimal fee, BigDecimal amount) {
        BigDecimal value = amount.multiply(fee).divide(BigDecimal.valueOf(ONE_HUNDRED), 2, HALF_EVEN);
        return value;
    }

}
