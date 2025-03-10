package com.cryptal.trading.repository;

import com.cryptal.trading.model.TwoFactorOtp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TwoFactorOtpRepository extends JpaRepository<TwoFactorOtp, String> {

    TwoFactorOtp findByUserId(Long userId);
}
