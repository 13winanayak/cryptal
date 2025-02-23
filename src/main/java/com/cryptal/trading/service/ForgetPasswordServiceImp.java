package com.cryptal.trading.service;

import com.cryptal.trading.domain.VERIFICATION_TYPE;
import com.cryptal.trading.model.ForgetPasswordToken;
import com.cryptal.trading.model.User;
import com.cryptal.trading.repository.ForgetpasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ForgetPasswordServiceImp implements ForgetPasswordService{

    @Autowired
    private ForgetpasswordRepository forgetpasswordRepository;
    
    @Override
    public ForgetPasswordToken createToken(User user, String id, String otp, VERIFICATION_TYPE verificationType, String sendTo) {
        ForgetPasswordToken token = new ForgetPasswordToken();
        token.setUser(user);
        token.setSendTo(sendTo);
        token.setVerificationType(verificationType);
        token.setOtp(otp);
        token.setId(id);

        return forgetpasswordRepository.save(token);

    }

    @Override
    public ForgetPasswordToken findById(String id) {
        Optional<ForgetPasswordToken> token = forgetpasswordRepository.findById(id);
        return token.orElse(null);
    }

    @Override
    public ForgetPasswordToken findByUser(Long userId) {
        return forgetpasswordRepository.findByUserId(userId);
    }

    @Override
    public void deleteToken(ForgetPasswordToken token) {
        forgetpasswordRepository.delete(token);
    }

}
