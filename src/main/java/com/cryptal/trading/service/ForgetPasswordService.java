package com.cryptal.trading.service;

import com.cryptal.trading.model.ForgetPasswordToken;
import com.cryptal.trading.domain.VERIFICATION_TYPE;
import com.cryptal.trading.model.User;

public interface ForgetPasswordService {
    ForgetPasswordToken createToken(User user,
                                    String id, String otp,
                                    VERIFICATION_TYPE verificationType,
                                    String sendTo);

    ForgetPasswordToken findById(String id);

    ForgetPasswordToken findByUser(Long userId);

    void deleteToken(ForgetPasswordToken token);

}
