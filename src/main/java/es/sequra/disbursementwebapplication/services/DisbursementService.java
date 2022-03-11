package es.sequra.disbursementwebapplication.services;

import es.sequra.disbursementwebapplication.domain.mappers.DisbursementMapper;
import es.sequra.disbursementwebapplication.domain.validators.GetDisbursementsValidator;
import es.sequra.disbursementwebapplication.infrastructure.entities.ConfigurationFee;
import es.sequra.disbursementwebapplication.infrastructure.entities.DisbursementEntity;
import es.sequra.disbursementwebapplication.infrastructure.entities.OrderEntity;
import es.sequra.disbursementwebapplication.infrastructure.repositories.ConfigurationFeeRepository;
import es.sequra.disbursementwebapplication.infrastructure.repositories.DisbursementRepository;
import es.sequra.disbursementwebapplication.infrastructure.repositories.OrderRepository;
import es.sequra.disbursementwebapplication.ui.presentation.DisbursementResponseDTO;
import es.sequra.disbursementwebapplication.ui.presentation.GetDisbursementsRequestDTO;
import es.sequra.disbursementwebapplication.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static es.sequra.disbursementwebapplication.infrastructure.entities.ConfigurationFee.LOWEST_FEE_VALUE;

@Service
@Slf4j
public class DisbursementService {

    @Autowired
    private DisbursementRepository disbursementRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ConfigurationFeeRepository configurationFeeRepository;


    public List<DisbursementResponseDTO> getDisbursements(GetDisbursementsRequestDTO requestDisbursementDTO) {
        Page<DisbursementEntity> result;
        List<DisbursementResponseDTO> serviceResponse = new ArrayList<>();

        int page = requestDisbursementDTO.getPage();
        int pageSize = requestDisbursementDTO.getPageSize();
        Pageable pageable = PageRequest.of(page, pageSize);

        /**
         * Getting the weekNumber and the Year from the LocalDate parameter that was given
         */
        Integer weekNumberOfYear = Utils.getWeekOfYearFromLocalDate(requestDisbursementDTO.getDateOfYear());
        Integer year = Utils.getWeekYearFromLocalDate(requestDisbursementDTO.getDateOfYear());

        /**
         * Here I choose to treat as invalid a CIF if the CIF parameter came as null
         */
        String merchantCIF = requestDisbursementDTO.getMerchantCIF();
        boolean isValidMerchantCIF = GetDisbursementsValidator.isMerchantCIFValid(merchantCIF);

        /**
         * Here there is a decision which method to call based on request params
         */
        if (isValidMerchantCIF) result = this.disbursementRepository.findAllByMerchantCifAndWeekOfYearAndYear(merchantCIF, weekNumberOfYear, year, pageable);
        else result = this.disbursementRepository.findAllByWeekOfYearAndYear(weekNumberOfYear, year, pageable);

        result.forEach(disbursementEntity -> {
            DisbursementResponseDTO responseDTO = DisbursementMapper.fromDisbursementEntity(disbursementEntity);
            serviceResponse.add(responseDTO);
        });

        return serviceResponse;
    }

    public void doDisbursementCalculations() {
        List<DisbursementEntity> disbursements = new ArrayList<>();
        // If today is monday, minus 7 will be previous monday
        Integer daysBefore = 7; // This cold be a parameter got from DB (configuration)

        /*
            Need to use a valid date, used by our dataset values
         */
//        LocalDate today = LocalDate.now();
        LocalDate today = LocalDate.now().withYear(2018).withMonth(1).withDayOfMonth(15);
        LocalDate todayMinus7days = today.minusDays(daysBefore);

        int weekOfYear = Utils.getWeekOfYearFromLocalDate(todayMinus7days);
        int year = Utils.getWeekYearFromLocalDate(todayMinus7days);

        // Getting yesterdayDate to get order by a range of dates
        LocalDate yesterdayDate = today.minusDays(1);
        Instant endDate = yesterdayDate.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant startDate = todayMinus7days.atStartOfDay().toInstant(ZoneOffset.UTC);

        Pageable pageRequest = PageRequest.of(0, 100);

        try {
            Page<OrderEntity> orderEntities = this.orderRepository
                    .findByCompletedAtNotNullAndCompletedAtIsBetween(startDate, endDate, pageRequest);
            while (orderEntities.hasNext() || orderEntities.isLast()) {

                orderEntities.forEach(orderEntity -> {
                    Boolean existsDisbursementForOrder = this.disbursementRepository
                            .existsByOrder_IdAndDeletedIsFalse(orderEntity.getId());

                    // if Exists, so there is no need to calculate another disbursement for this order;
                    // Skipping for the next;
                    if (existsDisbursementForOrder) return;

                    // Getting configurationFee
                    Optional<ConfigurationFee> optionalConfigurationFee = this.configurationFeeRepository
                            .findByOderAmount(orderEntity.getAmount());

                    // Setup default values
                    BigDecimal feeUsedToCharge = LOWEST_FEE_VALUE;
                    BigDecimal feeValue = ConfigurationFee.getFeeValue(feeUsedToCharge, orderEntity.getAmount());
                    BigDecimal disbursedAmount = orderEntity.getAmount().subtract(feeValue);

                    // Checking if configuration is present and sets new values so
                    if (optionalConfigurationFee.isPresent()) {
                        ConfigurationFee configurationFee = optionalConfigurationFee.get();
                        feeValue = configurationFee.getFeeValue(orderEntity.getAmount());
                        feeUsedToCharge = configurationFee.getFee();
                    }
                    DisbursementEntity disbursement = DisbursementEntity.builder()
                            .withMerchant(orderEntity.getMerchant())
                            .withYear(year)
                            .withWeekOfYear(weekOfYear)
                            .withFee(feeUsedToCharge)
                            .withFeeValueCharged(feeValue)
                            .withDisbursedAmount(disbursedAmount)
                            .withOrder(orderEntity)
                            .build();

                    this.disbursementRepository.save(disbursement);
                });
                if (orderEntities.hasNext() && !orderEntities.isLast()) {
                    pageRequest = orderEntities.nextOrLastPageable();
                    orderEntities = this.orderRepository
                            .findByCompletedAtNotNullAndCompletedAtIsBetween(startDate, endDate, pageRequest);
                } else {
                    break;
                }
            }

        } catch (Exception exception) {
            log.error("Error while try to create disbursement: {}", exception.getMessage());
            exception.printStackTrace();
        }

    }
}
