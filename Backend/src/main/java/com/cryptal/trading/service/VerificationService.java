package com.cryptal.trading.service;

import com.cryptal.trading.domain.VERIFICATION_TYPE;
import com.cryptal.trading.model.User;
import com.cryptal.trading.model.VerificationCode;

public interface VerificationService {

    VerificationCode sendVerificationOTP(User user, VERIFICATION_TYPE verificationType);

    VerificationCode findVerificationById(Long id) throws Exception;

    VerificationCode findUsersVerification(User user) throws Exception;

    Boolean VerifyOtp(String opt, VerificationCode verificationCode);

    void deleteVerification(VerificationCode verificationCode);
}
