package com.security_system.Service;

import com.security_system.Entity.SecurityGroup;
import com.security_system.Entity.User;
import com.security_system.Repository.SecurityGroupRepository;
import com.security_system.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {
    private final SecurityGroupRepository securityGroupRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
        SecurityGroupRepository securityGroupRepository,
        UserRepository userRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.securityGroupRepository = securityGroupRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void create(
        String email,
        String plainPassword
    ) {
        User user = User.builder()
            .email(email)
            .password(passwordEncoder.encode(plainPassword))
            .securityGroups(new HashSet<>())
            .build()
        ;
        SecurityGroup securityGroup = this.securityGroupRepository.findSecurityGroupByType(
            SecurityGroup.Type.USER
        );

        if (null != securityGroup) {
            user.getSecurityGroups().add(securityGroup);
        }

        this.userRepository.save(user);
    }

    public User read(String email) {
        return this.userRepository.findUserByEmail(email);
    }
}