package com.cryptal.trading.service;
import com.cryptal.trading.config.jwtProvider;
import com.cryptal.trading.domain.VERIFICATION_TYPE;
import com.cryptal.trading.model.User;
import com.cryptal.trading.model.TwoFactorAuth;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.cryptal.trading.repository.UserRepository;

public class UserServiceImp implements UserService{
	
	@Autowired 
	private UserRepository userRepository;

	@Override
	public User findUserProfileByJwt(String jwt) throws Exception {
		String email = jwtProvider.getEmailFromToken(jwt);
		User user = userRepository.findByEmail(email);

		if (user == null) {
		    throw new Exception("user not found");
		}

		return user;

	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		User user = userRepository.findByEmail(email);

		if (user == null) {
		    throw new Exception("user not found");
		}

		return user;
	}

	@Override
	public User findUserById(Long userId) throws Exception {
		Optional<User> user = userRepository.findById(userId);
		if (user.isEmpty()) {
		    throw new Exception("user not found");
		}
		return user.get();
	}
	
	

	@Override
	public User enableTwoFactorAuthentication(VERIFICATION_TYPE verification_type, String sendTo, User user) {
		TwoFactorAuth twoFactorAuth = new TwoFactorAuth();
		twoFactorAuth.setEnabled(true);
		twoFactorAuth.setSendto(verification_type);

		user.setTwoFactorAuth(twoFactorAuth);

		return userRepository.save(user);

	}

	@Override
	public User updatePassword(User user, String newPassword) {
		user.setPassword(newPassword);
		return userRepository.save(user);

	}

}
