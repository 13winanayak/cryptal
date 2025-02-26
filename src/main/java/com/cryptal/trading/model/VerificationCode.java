package com.cryptal.trading.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import com.cryptal.trading.domain.VERIFICATION_TYPE;
import lombok.Data;

@Entity
@Data
public class VerificationCode {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String otp;

    @OneToOne
    private User user;

    private String email;

    private String mobile;

    private VERIFICATION_TYPE verificationType;

    }

