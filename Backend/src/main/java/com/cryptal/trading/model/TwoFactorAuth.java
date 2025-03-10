package com.cryptal.trading.model;

import com.cryptal.trading.domain.VERIFICATION_TYPE;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TwoFactorAuth {

    private boolean isEnabled = false;
    private VERIFICATION_TYPE sendTo;

    public boolean isEnabled() {
        return isEnabled;
    }

    public VERIFICATION_TYPE getSendTo() {
        return sendTo;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setSendTo(VERIFICATION_TYPE sendTo) {
        this.sendTo = sendTo;
    }
}
