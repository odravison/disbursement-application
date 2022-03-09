package es.sequra.disbursementwebapplication.infrastructure.repositories;

import es.sequra.disbursementwebapplication.infrastructure.entities.DisbursementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisbursementRepository extends PagingAndSortingRepository<DisbursementEntity, Long> {

    Page<DisbursementEntity> findAllByMerchantCifAndWeekOfYearAndYear(String cif,
                                                                      Integer weekOfYear,
                                                                      Integer year,
                                                                      Pageable pageable);

    Page<DisbursementEntity> findAllByWeekOfYearAndYear(Integer weekNumberOfYear, Integer year, Pageable pageable);
}
