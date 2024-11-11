package com.IAPDemoPOC.Subscription.auth;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.IAPDemoPOC.Subscription.models.User;
import com.IAPDemoPOC.Subscription.repositories.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository; 
    private final String jwtSecret = "sachisveryveryveryveryveryveryverycool"; 
    private SecretKey key = Keys.hmacShaKeyFor("sachisveryveryveryveryveryveryverycool".getBytes(StandardCharsets.UTF_8));
	
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");

        String email = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7); // Extract the token without "Bearer "
            try {
            	Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
//            	Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody();
                email = claims.getSubject(); 
            } catch (SignatureException e) {
                // Invalid JWT signature
                logger.warn("Invalid JWT signature");
            }
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	 Optional<User> userOptional = userRepository.findByEmail(email);
        	 if (userOptional.isEmpty()) {
                 throw new RuntimeException("User with email: " + email + " not found.");
             }
            User user = userOptional.get();
            
            if (user != null && validateToken(jwt, user)) {
                UsernamePasswordAuthenticationToken authenticationToken = 
                    new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()); 
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        chain.doFilter(request, response);
    }

    private boolean validateToken(String token, User user) {
    	Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
//    	Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        String email = claims.getSubject();
        return (email.equals(user.getEmail()) && !claims.getExpiration().before(new Date()));
    }
}
