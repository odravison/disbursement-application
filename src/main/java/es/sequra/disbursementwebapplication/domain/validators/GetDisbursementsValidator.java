package es.sequra.disbursementwebapplication.domain.validators;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class GetDisbursementsValidator {
    public static boolean isMerchantCIFValid(String merchantCIF) {
        return Objects.nonNull(merchantCIF)
                && StringUtils.isNotEmpty(merchantCIF)
                && StringUtils.isNotBlank(merchantCIF);
    }
}
