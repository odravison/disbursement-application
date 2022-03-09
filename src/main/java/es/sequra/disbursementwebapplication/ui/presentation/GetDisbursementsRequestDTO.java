package es.sequra.disbursementwebapplication.ui.presentation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class GetDisbursementsRequestDTO {

    private LocalDate dateOfYear;
    private String merchantCIF;
    private int pageSize;
    private int page;

}
