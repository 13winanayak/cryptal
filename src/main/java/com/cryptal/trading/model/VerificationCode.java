package com.cryptal.trading.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import com.cryptal.trading.domain.VERIFICATION_TYPE;

@Entity
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

        // Getters and Setters

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getOtp() {
            return otp;
        }



        public void setOtp(String otp) {
            this.otp = otp;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public VERIFICATION_TYPE getVerificationType() {
            return verificationType;
        }

        public void setVerificationType(VERIFICATION_TYPE verificationType) {
            this.verificationType = verificationType;
        }
    }

