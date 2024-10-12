package com.vdb.auth.server.application;

import com.vdb.auth.server.api.CreateUserRequest;
import com.vdb.auth.server.infrastructure.User;
import com.vdb.auth.server.infrastructure.UserRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class UserCommandHandler {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserCommandHandler(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Async
    public CompletableFuture<User> handleCreateUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalStateException("Email already registered");
        }
        User user = UserMapper.INSTANCE.createUserRequestToUser( request );
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        return CompletableFuture.supplyAsync(() -> {
            try {
                return userRepository.save(user);
            } catch (Exception e) {
                // Handle any other exceptions
                throw new CompletionException(new Exception("An unexpected error occurred", e));
            }
        });
    }
}
