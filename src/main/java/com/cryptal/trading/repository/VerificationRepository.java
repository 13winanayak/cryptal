package com.cryptal.trading.repository;

import com.cryptal.trading.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<VerificationCode, Long> {

    public  VerificationCode findByUserId(Long userId);
}
