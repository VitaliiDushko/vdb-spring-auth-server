package com.vdb.auth.server.infrastructure;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "main")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(name = "id", nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "email", nullable = false, length = 254)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    private String surname;

    @Column(name = "role", nullable = false, length = 50)
    private String role;
}
