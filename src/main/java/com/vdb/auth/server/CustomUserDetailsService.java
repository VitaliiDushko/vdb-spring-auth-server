package com.vdb.auth.server;

import com.vdb.auth.server.infrastructure.User;
import com.vdb.auth.server.infrastructure.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user from the database using UserRepository
        User user = userRepository.findOneByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole()));

        // Return a Spring Security User object, containing the username and the hashed password
        return new CustomUserDetails(
                user.getEmail(),
                user.getPasswordHash(),
                user.getId(),
                user.getName(),
                user.getSurname(),
                authorities
        );
    }
}
