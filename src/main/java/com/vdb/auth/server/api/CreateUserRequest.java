package com.vdb.auth.server.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank @Size(max = 50) String email,
        @NotBlank @Size(max = 255) String password,
        @NotBlank @Size(max = 50) String name,
        @NotBlank @Size(max = 254) String surname,
        @NotBlank @Size(max = 50) String role
) {}