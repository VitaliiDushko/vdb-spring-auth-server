package com.vdb.auth.server;

import com.vdb.auth.server.infrastructure.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CustomOidcTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {

    public CustomOidcTokenCustomizer() {
    }

    @Override
    public void customize(JwtEncodingContext context) {
        if (context.getTokenType().getValue().equals("id_token")) {
            Authentication authentication = context.getPrincipal();
            // Ensure the principal is an instance of CustomUserDetails
            if (authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
                // Set custom claims in the ID token
                JwtClaimsSet.Builder claims = context.getClaims();
                claims.claim("sub", userDetails.getUserId().toString());  // Set UUID as 'sub'
                claims.claim("username", userDetails.getUsername());
                claims.claim("name", userDetails.getName());  // Add user's first name
                claims.claim("surname", userDetails.getSurname());  // Add user's
                claims.claim("roles", userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList());  // Add roles
            }
        }
    }
}
