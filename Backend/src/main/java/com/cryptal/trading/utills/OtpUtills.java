package com.cryptal.trading.utills;

import java.util.Random;
public class OtpUtills {


    public static String generateOTP() {
        int otpLength = 6;
        Random random = new Random();
        StringBuilder otp = new StringBuilder(otpLength);

        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10)); // Generates a random digit (0-9)
        }

        return otp.toString();
    }

}
