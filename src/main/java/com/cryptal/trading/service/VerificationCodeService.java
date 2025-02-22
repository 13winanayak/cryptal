package com.cryptal.trading.service;

import com.cryptal.trading.domain.VERIFICATION_TYPE;
import com.cryptal.trading.model.User;
import com.cryptal.trading.model.VerificationCode;

public interface VerificationCodeService {

    VerificationCode sendVerificationCode(User user, VERIFICATION_TYPE verification_type);

    VerificationCode getVerificationCodeById(Long id)throws Exception;

    VerificationCode getVerificationCodeByUser (Long userId);

    void deleteVerificationCodeById(VerificationCode verificationCode);
}
