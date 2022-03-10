package es.sequra.disbursementwebapplication.infrastructure.repositories;

import es.sequra.disbursementwebapplication.infrastructure.entities.ShopperEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ShopperRepository extends PagingAndSortingRepository<ShopperEntity, Long> {
}