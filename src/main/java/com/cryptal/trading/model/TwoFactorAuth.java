package com.cryptal.trading.model;

import com.cryptal.trading.domain.VERIFICATION_TYPE;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TwoFactorAuth {

    private boolean isEnabled = false;
    private VERIFICATION_TYPE sendTo;
}
