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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public VERIFICATION_TYPE getVerificationType() {
        return verificationType;
    }

    public void setVerificationType(VERIFICATION_TYPE verificationType) {
        this.verificationType = verificationType;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }
}
