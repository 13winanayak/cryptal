package com.cryptal.trading.response;

import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private boolean status;
    private String message;
    private boolean isTwoFactorAuthEnabled;
    private String session;


    public String getMessage() {
        return message;
    }

    public String getJwt() {
        return jwt;
    }

    public boolean isStatus() {
        return status;
    }

    public boolean isTwoFactorAuthEnabled() {
        return isTwoFactorAuthEnabled;
    }

    public String getSession() {
        return session;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public void setTwoFactorAuthEnabled(boolean twoFactorAuthEnabled) {
        isTwoFactorAuthEnabled = twoFactorAuthEnabled;
    }
}
