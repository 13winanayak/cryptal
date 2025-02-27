package com.cryptal.trading.controller;

import com.cryptal.trading.exception.UserException;
import com.cryptal.trading.model.ForgetPasswordToken;
import com.cryptal.trading.request.ForgetPasswordTokenReq;
import com.cryptal.trading.request.ResetPasswordReq;
import com.cryptal.trading.request.UpdatePasswordReq;
import com.cryptal.trading.response.ApiResponse;
import com.cryptal.trading.response.AuthResponse;
import com.cryptal.trading.service.*;
import com.cryptal.trading.domain.VERIFICATION_TYPE;
import com.cryptal.trading.utills.OtpUtills;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cryptal.trading.model.User;
import com.cryptal.trading.model.VerificationCode;
import com.cryptal.trading.service.UserService;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@RestController
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private VerificationService verificationService;

    @Autowired
    private ForgetPasswordService forgotPasswordService;

    @Autowired
    private EmailService emailService;


    @GetMapping("/api/users/profile")
    public ResponseEntity<User> getUserProfileHandler(
            @RequestHeader("Authorization") String jwt) throws UserException {

        User user = userService.findUserProfileByJwt(jwt);
        user.setPassword(null);

        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<User> findUserById(
            @PathVariable Long userId,
            @RequestHeader("Authorization") String jwt) throws UserException {

        User user = userService.findUserById(userId);
        user.setPassword(null);

        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/users/email/{email}")
    public ResponseEntity<User> findUserByEmail(
            @PathVariable String email,
            @RequestHeader("Authorization") String jwt) throws UserException {

        User user = userService.findUserByEmail(email);

        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }

    @PatchMapping("/api/users/enable-two-factor/verify-otp/{otp}")
    public ResponseEntity<User> enabledTwoFactorAuthentication(
            @RequestHeader("Authorization") String jwt,
            @PathVariable String otp
    ) throws Exception, UserException {


        User user = userService.findUserProfileByJwt(jwt);


        VerificationCode verificationCode = verificationService.findUsersVerification(user);

        String sendTo=verificationCode.getVerificationType().equals(VERIFICATION_TYPE.EMAIL)?verificationCode.getEmail():verificationCode.getMobile();


        boolean isVerified = verificationService.VerifyOtp(otp, verificationCode);

        if (isVerified) {
            User updatedUser = userService.enabledTwoFactorAuthentication(verificationCode.getVerificationType(),
                    sendTo,user);
            verificationService.deleteVerification(verificationCode);
            return ResponseEntity.ok(updatedUser);
        }
        throw new Exception("wrong otp");

    }



    @PatchMapping("/auth/users/reset-password/verify-otp")
    public ResponseEntity<ApiResponse> resetPassword(
            @RequestParam String id,
            @RequestBody ResetPasswordReq req
    ) throws Exception {
        ForgetPasswordToken forgotPasswordToken=forgotPasswordService.findById(id);

        boolean isVerified = forgotPasswordService.verifyToken(forgotPasswordToken,req.getOtp());

        if (isVerified) {

            userService.updatePassword(forgotPasswordToken.getUser(),req.getPassword());
            ApiResponse apiResponse=new ApiResponse();
            apiResponse.setMessage("password updated successfully");
            return ResponseEntity.ok(apiResponse);
        }
        throw new Exception("wrong otp");

    }

    @PostMapping("/auth/users/reset-password/send-otp")
    public ResponseEntity<AuthResponse> sendUpdatePasswordOTP(
            @RequestBody UpdatePasswordReq req)
            throws Exception, UserException {

        User user = userService.findUserByEmail(req.getSendTo());
        String otp= OtpUtills.generateOTP();
        UUID uuid = UUID.randomUUID();
        String id = uuid.toString();

        ForgetPasswordToken token = forgotPasswordService.findByUser(user.getId());

        if(token==null){
            token=forgotPasswordService.createToken(
                    user,id,otp,req.getVerificationType(), req.getSendTo()
            );
        }

        if(req.getVerificationType().equals(VERIFICATION_TYPE.EMAIL)){
            emailService.sendVerificationOtpEmail(
                    user.getEmail(),
                    token.getOtp()
            );
        }

        AuthResponse res=new AuthResponse();
        res.setSession(token.getId());
        res.setMessage("Password Reset OTP sent successfully.");

        return ResponseEntity.ok(res);

    }

    @PatchMapping("/api/users/verification/verify-otp/{otp}")
    public ResponseEntity<User> verifyOTP(
            @RequestHeader("Authorization") String jwt,
            @PathVariable String otp
    ) throws Exception, UserException {


        User user = userService.findUserProfileByJwt(jwt);


        VerificationCode verificationCode = verificationService.findUsersVerification(user);


        boolean isVerified = verificationService.VerifyOtp(otp, verificationCode);

        if (isVerified) {
            verificationService.deleteVerification(verificationCode);
            User verifiedUser = userService.verifyUser(user);
            return ResponseEntity.ok(verifiedUser);
        }
        throw new Exception("wrong otp");

    }

    @PostMapping("/api/users/verification/{verificationType}/send-otp")
    public ResponseEntity<String> sendVerificationOTP(
            @PathVariable VERIFICATION_TYPE verificationType,
            @RequestHeader("Authorization") String jwt)
            throws Exception, UserException {

        User user = userService.findUserProfileByJwt(jwt);

        VerificationCode verificationCode = verificationService.findUsersVerification(user);

        if(verificationCode == null) {
            verificationCode = verificationService.sendVerificationOTP(user,verificationType);
        }


        if(verificationType.equals(VERIFICATION_TYPE.EMAIL)){
            emailService.sendVerificationOtpEmail(user.getEmail(), verificationCode.getOtp());
        }



        return ResponseEntity.ok("Verification OTP sent successfully.");

    }


}
