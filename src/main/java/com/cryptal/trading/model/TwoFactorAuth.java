package com.cryptal.trading.model;

import com.cryptal.trading.domain.VERIFICATION_TYPE;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TwoFactorAuth {
    private boolean isEnabled = false;
    private VERIFICATION_TYPE sendto;

    public boolean isEnabled() {
        return isEnabled;
    }

    public VERIFICATION_TYPE getSendto() {
        return sendto;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public void setSendto(VERIFICATION_TYPE sendto) {
        this.sendto = sendto;
    }
}
