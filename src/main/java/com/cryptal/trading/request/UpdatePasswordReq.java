package com.cryptal.trading.request;

import com.cryptal.trading.domain.VERIFICATION_TYPE;
import lombok.Data;

@Data
public class UpdatePasswordReq {

    private String sendTo;
    private VERIFICATION_TYPE verificationType;
}
