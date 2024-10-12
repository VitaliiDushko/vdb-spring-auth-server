package com.vdb.auth.server.api;

import com.vdb.auth.server.application.UserCommandHandler;
import com.vdb.auth.server.infrastructure.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/auth")
public class UsersController {

    private final UserCommandHandler userCommandHandler;

    public UsersController(UserCommandHandler userCommandHandler) {
        this.userCommandHandler = userCommandHandler;
    }

    @PostMapping("/register")
    public CompletableFuture<User> registerUser(@RequestBody CreateUserRequest user) {
        return userCommandHandler.handleCreateUser(user);
    }
}