package com.example.cloneinstagramback.service;

import com.example.cloneinstagramback.repository.UserRepository;
import com.example.cloneinstagramback.insta.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRep;
    @Autowired
    private AopAutoConfiguration aopAutoConfiguration;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> opt = userRep.findByEmail(username);

        if (opt.isPresent()) {
            User user = opt.get();
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            return (UserDetails) new User(user.getEmail(), user.getPassword(), grantedAuthorities);
        }

        throw new BadCredentialsException("user not found with user name"+ username);
    }


}
