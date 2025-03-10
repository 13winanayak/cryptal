package com.cryptal.trading.request;

import com.cryptal.trading.domain.VERIFICATION_TYPE;
import lombok.Data;

@Data
public class UpdatePasswordReq {

    private String sendTo;
    private VERIFICATION_TYPE verificationType;

    public VERIFICATION_TYPE getVerificationType() {
        return verificationType;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    public void setVerificationType(VERIFICATION_TYPE verificationType) {
        this.verificationType = verificationType;
    }
}
