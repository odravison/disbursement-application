package es.sequra.disbursementwebapplication.infrastructure.repositories;

import es.sequra.disbursementwebapplication.infrastructure.entities.MerchantEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface MerchantRepository extends PagingAndSortingRepository<MerchantEntity, Long> {

    Optional<MerchantEntity> findByCif(String cif);

}