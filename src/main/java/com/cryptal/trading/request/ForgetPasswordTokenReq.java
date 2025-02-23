package com.cryptal.trading.request;

import com.cryptal.trading.domain.VERIFICATION_TYPE;
import lombok.Data;

@Data
public class ForgetPasswordTokenReq {

    private String sendTo;
    private VERIFICATION_TYPE verification_type;



    public VERIFICATION_TYPE getVerificationType() {
        return verification_type;
    }

    public void setVerificationType(VERIFICATION_TYPE verification_type) {
        this.verification_type = verification_type;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

}
