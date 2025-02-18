package com.cryptal.trading.service;

import com.cryptal.trading.model.User;
import java.util.List;
import java.util.ArrayList;
import com.cryptal.trading.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user = userRepository.findByEmail(username);
if(user==null){
        throw new UsernameNotFoundException(username);
    }

    List<GrantedAuthority> authorityList = new ArrayList<>();
    return new org.springframework.security.core.userdetails.User(user.getEmail(),
        user.getPassword(),authorityList);
         }
}
