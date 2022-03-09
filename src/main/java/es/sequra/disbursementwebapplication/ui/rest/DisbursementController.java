package es.sequra.disbursementwebapplication.ui.rest;

import es.sequra.disbursementwebapplication.ui.presentation.DisbursementsResponseDTO;
import es.sequra.disbursementwebapplication.ui.presentation.GetDisbursementsRequestDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("disbursement")
public class DisbursementController {

    public static final String

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public DisbursementsResponseDTO getDisbursements(GetDisbursementsRequestDTO requestDisbursementDTO) {
        return null;
    }
}
