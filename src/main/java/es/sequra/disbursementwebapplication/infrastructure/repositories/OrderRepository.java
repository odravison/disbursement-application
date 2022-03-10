package es.sequra.disbursementwebapplication.infrastructure.repositories;

import es.sequra.disbursementwebapplication.infrastructure.entities.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.Instant;
import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Long> {

    Page<OrderEntity> findByCompletedAtNotNullAndCompletedAtIsBetween(Instant start, Instant end, Pageable pageable);

}
