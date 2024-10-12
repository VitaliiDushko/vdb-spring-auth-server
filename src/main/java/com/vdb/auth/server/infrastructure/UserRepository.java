package com.vdb.auth.server.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findOneByEmail(String email);
    boolean existsByEmail(String email);
}
