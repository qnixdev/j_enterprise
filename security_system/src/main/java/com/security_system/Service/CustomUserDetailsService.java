package com.security_system.Service;

import com.security_system.Model.UserPrincipal;
import com.security_system.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(
        UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userRepository.findUserByEmail(email);

        if (null == user) {
            throw new UsernameNotFoundException(
                String.format("User with email: '%s' not found.", email)
            );
        }

        return new UserPrincipal(user);
    }
}