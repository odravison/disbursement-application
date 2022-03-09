package es.sequra.disbursementwebapplication.ui.rest;

import es.sequra.disbursementwebapplication.services.DisbursementService;
import es.sequra.disbursementwebapplication.ui.presentation.DisbursementResponseDTO;
import es.sequra.disbursementwebapplication.ui.presentation.DisbursementsResponseDTO;
import es.sequra.disbursementwebapplication.ui.presentation.GetDisbursementsRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("disbursement")
public class DisbursementController {

    @Autowired
    private DisbursementService disbursementService;

    /**
     * This is the endpoint /disbursement
     * it receive as request params merchantCIF, the date of week desired,
     * page and pageSize, these are optional
     * @param requestDisbursementDTO
     * @return DisbursementsResponse with a list of disbursement found by the params
     */
    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public DisbursementsResponseDTO getDisbursements(@Valid GetDisbursementsRequestDTO requestDisbursementDTO) {

        DisbursementsResponseDTO response;
        List<DisbursementResponseDTO> disbursementsResponse = disbursementService.getDisbursements(requestDisbursementDTO);

        response = DisbursementsResponseDTO.createDisbursementsResponseDTO(disbursementsResponse);

        return response;
    }
}
