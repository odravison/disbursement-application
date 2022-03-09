package es.sequra.disbursementwebapplication.ui.presentation;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DisbursementsResponseDTO {
    private List<DisbursementResponseDTO> disbursements = new ArrayList<>();

    public static DisbursementsResponseDTO createDisbursementsResponseDTO(List<DisbursementResponseDTO> disbursements) {
        DisbursementsResponseDTO response = new DisbursementsResponseDTO();
        response.setDisbursements(disbursements);
        return response;
    }
}
