package com.cryptal.trading.controller;

import com.cryptal.trading.service.EmailService;
import com.cryptal.trading.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cryptal.trading.model.User;
import com.cryptal.trading.service.UserService;
import org.springframework.http.HttpStatus;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/api/users/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

//    @PostMapping("/api/users/verification{verificationType}/send-otp")
//    public ResponseEntity<User> enableTwoFactorAuthentication(@RequestHeader("Authorization") String jwt) {
//        User user = userService.findUserProfileByJwt(jwt);
//
//        return new ResponseEntity<User>(user, HttpStatus.OK);
//    }
//
//    @PostMapping("/api/users/enable-two-factor/verify-otp/{otp}")
//    public ResponseEntity<User> enableTwoFactorAuthentication(@RequestHeader("Authorization") String jwt) {
//        User user = userService.findUserProfileByJwt(jwt);
//
//        return new ResponseEntity<User>(user, HttpStatus.OK);
//    }

}
