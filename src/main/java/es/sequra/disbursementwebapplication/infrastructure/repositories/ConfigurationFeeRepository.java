package es.sequra.disbursementwebapplication.infrastructure.repositories;

import es.sequra.disbursementwebapplication.infrastructure.entities.ConfigurationFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface ConfigurationFeeRepository extends JpaRepository<ConfigurationFee, Long> {

    @Query(value = "select * from configuration_fee cf where cf.range_floor_amount <= ?1 and cf.range_ceil_amount >= ?1"
    , nativeQuery = true)
    Optional<ConfigurationFee> findByOderAmount(BigDecimal orderAmount);
}
