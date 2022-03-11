package es.sequra.disbursementwebapplication.ui.presentation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class GetDisbursementsRequestDTO {

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfYear;
    private String merchantCIF;
    @Min(10)
    private int pageSize = 10;
    @Min(0)
    private int page = 1;
}
