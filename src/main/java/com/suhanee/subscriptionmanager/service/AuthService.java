// AuthService.java

package com.suhanee.subscriptionmanager.service;

import com.suhanee.subscriptionmanager.entity.UserEntity;
import com.suhanee.subscriptionmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public String signup(UserEntity request) {

        UserEntity user = new UserEntity();

        user.setEmail(request.getEmail());

        user.setPassword(
                passwordEncoder.encode(
                        request.getPassword()
                )
        );

        userRepository.save(user);

        return "User registered successfully";
    }

    public Map<String, String> login(
            UserEntity request
    ) {

        UserEntity user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(
                        () -> new RuntimeException(
                                "User not found"
                        )
                );

        boolean isPasswordCorrect =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword()
                );

        if (!isPasswordCorrect) {

            throw new RuntimeException(
                    "Invalid password"
            );
        }

        String token =
                jwtService.generateToken(
                        user.getEmail()
                );

        return Map.of(
                "token",
                token
        );
    }
}