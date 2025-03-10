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
    private ForgetpasswordRepository forgotPasswordRepository;

    @Override
    public ForgetPasswordToken createToken(User user,
                                           String id,
                                           String otp,
                                           VERIFICATION_TYPE verificationType,
                                           String sendTo
    ) {
        ForgetPasswordToken forgotPasswordToken=new ForgetPasswordToken();
        forgotPasswordToken.setUser(user);
        forgotPasswordToken.setId(id);
        forgotPasswordToken.setOtp(otp);
        forgotPasswordToken.setVerificationType(verificationType);
        forgotPasswordToken.setSendTo(sendTo);

        return forgotPasswordRepository.save(forgotPasswordToken);
    }

    @Override
    public ForgetPasswordToken findById(String id) {
        Optional<ForgetPasswordToken> opt=forgotPasswordRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public ForgetPasswordToken findByUser(Long userId) {
        return forgotPasswordRepository.findByUserId(userId);
    }

    @Override
    public void deleteToken(ForgetPasswordToken token) {

        forgotPasswordRepository.delete(token);

    }

    @Override
    public boolean verifyToken(ForgetPasswordToken token, String otp) {
        return token.getOtp().equals(otp);
    }

}
