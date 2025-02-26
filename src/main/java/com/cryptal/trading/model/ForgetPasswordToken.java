package com.cryptal.trading.model;

import jakarta.persistence.*;

import com.cryptal.trading.domain.VERIFICATION_TYPE;
import lombok.Data;

@Entity
@Data
public class ForgetPasswordToken {


    @Id
    private String id;

    @OneToOne
    private User user;

    private String otp;

    private VERIFICATION_TYPE verificationType;

    private String sendTo;
}
