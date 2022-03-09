package es.sequra.disbursementwebapplication.ui;

import es.sequra.disbursementwebapplication.ui.presentation.DisbursementsResponseDTO;
import es.sequra.disbursementwebapplication.ui.presentation.GetDisbursementsRequestDTO;
import es.sequra.disbursementwebapplication.ui.rest.DisbursementController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.temporal.IsoFields;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class DisbursementControllerTest {

    @Autowired
    private DisbursementController api;

    // 1. Get the disbursement for a given merchant on a given week
    @Test
    public void getDisbursementsForAGivenMerchantOnAGivenWeek() {

        String merchantCIFSearched = "B611111110";
        LocalDate searchedDate = LocalDate.parse("2017-08-01");
        GetDisbursementsRequestDTO requestDisbursementDTO = new GetDisbursementsRequestDTO();
        requestDisbursementDTO.setDateOfYear(searchedDate);
        requestDisbursementDTO.setMerchantCIF(merchantCIFSearched);

        // Calling the domain api
        DisbursementsResponseDTO disbursementsResponseDTO = this.api.getDisbursements(requestDisbursementDTO);

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

        // Calling the domain api
        DisbursementsResponseDTO disbursementsResponseDTO = this.api.getDisbursements(requestDisbursementDTO);

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
