package es.sequra.disbursementwebapplication.ui;

import es.sequra.disbursementwebapplication.ui.presentation.DisbursementsResponseDTO;
import es.sequra.disbursementwebapplication.ui.presentation.GetDisbursementsRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.temporal.IsoFields;

import static es.sequra.disbursementwebapplication.ui.rest.DisbursementController.GET_DISBURSEMENT_URL;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class DisbursementControllerTest {

    @Autowired
    protected TestRestTemplate restTemplate;

    // 1. Get the disbursement for a given merchant on a given week
    @Test
    public void getDisbursementsForAGivenMerchantOnAGivenWeek() {

        String merchantCIFSearched = "B611111112";
        LocalDate searchedDate = LocalDate.parse("2018-01-14");
        GetDisbursementsRequestDTO requestDisbursementDTO = new GetDisbursementsRequestDTO();
        requestDisbursementDTO.setDateOfYear(searchedDate);
        requestDisbursementDTO.setMerchantCIF(merchantCIFSearched);

        // Calling api
        ResponseEntity<DisbursementsResponseDTO> response = restTemplate.exchange(
                GET_DISBURSEMENT_URL,
                HttpMethod.POST,
                new HttpEntity<>(requestDisbursementDTO),
                DisbursementsResponseDTO.class);

        DisbursementsResponseDTO disbursementsResponseDTO = response.getBody();

        int weekSearched = searchedDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        int yearSearched = searchedDate.get(IsoFields.WEEK_BASED_YEAR);

        Boolean invalidResult = disbursementsResponseDTO
                .getDisbursements().stream()
                .anyMatch(disbursement -> {

                    boolean invalidDisbursementResult;
                    int orderWeekResult = disbursement.getWeekOfYear();
                    int orderYearResult = disbursement.getWeekOfYear();
                    String merchantCIFResult = disbursement.getMerchantCIF();

                    /**
                     * Checking if there is some invalid values in disbursement result set
                     */
                    invalidDisbursementResult = (orderWeekResult != weekSearched)
                            || (orderYearResult != yearSearched)
                            || (merchantCIFResult != merchantCIFSearched);

                    return invalidDisbursementResult;

                });

        assertFalse(invalidResult);
    }

    // 2. Get the disbursement of all merchant on a given week
    @Test
    public void getDisbursementsForAllMerchantOnAGivenWeek() {

        LocalDate searchedDate = LocalDate.parse("2017-08-01");
        GetDisbursementsRequestDTO requestDisbursementDTO = new GetDisbursementsRequestDTO();
        requestDisbursementDTO.setDateOfYear(searchedDate);
        requestDisbursementDTO.setMerchantCIF("");

        // Calling api
        ResponseEntity<DisbursementsResponseDTO> response = restTemplate.exchange(
                GET_DISBURSEMENT_URL,
                HttpMethod.POST,
                new HttpEntity<>(requestDisbursementDTO),
                DisbursementsResponseDTO.class);

        DisbursementsResponseDTO disbursementsResponseDTO = response.getBody();

        int weekSearched = searchedDate.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
        int yearSearched = searchedDate.get(IsoFields.WEEK_BASED_YEAR);

        Boolean invalidResult = disbursementsResponseDTO
                .getDisbursements().stream()
                .anyMatch(disbursement -> {

                    boolean invalidDisbursementResult;
                    int orderWeekResult = disbursement.getWeekOfYear();
                    int orderYearResult = disbursement.getWeekOfYear();

                    /**
                     * Checking if there is some invalid values in disbursement result set
                     */
                    invalidDisbursementResult = (orderWeekResult != weekSearched) || (orderYearResult != yearSearched);

                    return invalidDisbursementResult;

                });

        assertFalse(invalidResult);

    }

}
