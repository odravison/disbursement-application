package es.sequra.disbursementwebapplication.domain.mappers;

import es.sequra.disbursementwebapplication.infrastructure.entities.DisbursementEntity;
import es.sequra.disbursementwebapplication.ui.presentation.DisbursementResponseDTO;
import es.sequra.disbursementwebapplication.utils.MapperUtils;

public class DisbursementMapper {

    public static DisbursementResponseDTO fromDisbursementEntity(DisbursementEntity disbursementEntity) {
        DisbursementResponseDTO responseDTO = MapperUtils.mapTo(disbursementEntity, DisbursementResponseDTO.class);
        responseDTO.setMerchantCIF(disbursementEntity.getMerchant().getCif());
        return responseDTO;
    }

}
