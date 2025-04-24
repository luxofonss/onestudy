package com.quyennv.lms.service;

import com.quyennv.lms.entities.User;
import com.quyennv.lms.entities.Users;
import com.quyennv.lms.repository.UserRepository;
import com.quyennv.lms.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsernameForAuth(username).orElseThrow(
                ()-> new UsernameNotFoundException("User not found")
        );

        return UserPrincipal.from(user);
    }
}
