package es.sequra.disbursementwebapplication.services;

import es.sequra.disbursementwebapplication.domain.mappers.DisbursementMapper;
import es.sequra.disbursementwebapplication.domain.validators.GetDisbursementsValidator;
import es.sequra.disbursementwebapplication.infrastructure.entities.DisbursementEntity;
import es.sequra.disbursementwebapplication.infrastructure.repositories.DisbursementRepository;
import es.sequra.disbursementwebapplication.ui.presentation.DisbursementResponseDTO;
import es.sequra.disbursementwebapplication.ui.presentation.GetDisbursementsRequestDTO;
import es.sequra.disbursementwebapplication.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DisbursementService {

    @Autowired
    private DisbursementRepository disbursementRepository;


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
}
