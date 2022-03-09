package es.sequra.disbursementwebapplication.ui.presentation;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DisbursementResponseDTO {

    private String merchantCIF;
    private BigDecimal fee;
    private BigDecimal disbursedAmount;
    private Integer weekOfYear;
    private Integer year;

}
