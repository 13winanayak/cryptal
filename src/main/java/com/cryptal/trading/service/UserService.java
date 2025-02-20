package com.cryptal.trading.service;

import com.cryptal.trading.domain.VERIFICATION_TYPE;
import com.cryptal.trading.model.User;

public interface UserService {

    public User findUserProfileByJwt(String jwt) throws Exception;
    public User findUserByEmail(String email) throws Exception;
    public User findUserById(Long userId)throws Exception;;

    public User enableTwoFactorAuthentication(VERIFICATION_TYPE verification_type, String sendTo,  User user);

    User updatePassword(User user, String newPassword);

}
