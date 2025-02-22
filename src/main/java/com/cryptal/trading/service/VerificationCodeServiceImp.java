package com.cryptal.trading.service;

import com.cryptal.trading.model.User;
import com.cryptal.trading.model.VerificationCode;
import com.cryptal.trading.repository.VerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.cryptal.trading.utills.OtpUtills;
import org.springframework.stereotype.Service;
import com.cryptal.trading.domain.VERIFICATION_TYPE;

import java.util.Optional;

@Service
public class VerificationCodeServiceImp implements VerificationCodeService{

    @Autowired
    private VerificationRepository verificationRepository;

    @Override
    public VerificationCode sendVerificationCode(User user, VERIFICATION_TYPE verificationType) {
        VerificationCode verificationCode1 = new VerificationCode();
        verificationCode1.setOtp(OtpUtills.generateOTP());
        verificationCode1.setVerificationType(verificationType);
        verificationCode1.setUser(user);

        return verificationRepository.save(verificationCode1);
    }

    @Override
    public VerificationCode getVerificationCodeById(Long id) throws Exception {
        Optional<VerificationCode> verificationCode = verificationRepository.findById(id);
        if (verificationCode.isPresent()) {
            return verificationCode.get();
        }
        throw new Exception("verification code not found");
    }

    @Override
    public VerificationCode getVerificationCodeByUser(Long userId) {
        return verificationRepository.findByUserId(userId);
    }

    @Override
    public void deleteVerificationCodeById(VerificationCode verificationCode) {
        verificationRepository.delete(verificationCode);
    }
}
