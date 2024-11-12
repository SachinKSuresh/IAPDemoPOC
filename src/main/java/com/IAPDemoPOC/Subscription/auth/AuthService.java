package com.IAPDemoPOC.Subscription.auth;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.crypto.SecretKey;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.IAPDemoPOC.Subscription.models.User;
import com.IAPDemoPOC.Subscription.repositories.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultJwtBuilder;
import io.jsonwebtoken.security.Keys;


@Service
public class AuthService {
	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private SecretKey key = Keys.hmacShaKeyFor("sachisveryveryveryveryveryveryverycool".getBytes(StandardCharsets.UTF_8));
	// private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	public AuthService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	
	public boolean signUp(String email, String password,String userName) throws RuntimeException {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("User with email: " + email + " already exists");
        }

        User user = new User();

        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setUserName(userName);
        userRepository.save(user);

        return true;
    }

    public String login(String email, String password) throws RuntimeException {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User with email: " + email + " not found.");
        }

        boolean matches = bCryptPasswordEncoder.matches(
                password,
                userOptional.get().getPassword()
        );

        if (matches) {
            String token =  createJwtToken(userOptional.get().getId(),
                    userOptional.get().getEmail());

            

            return token;
        } else {
            throw new RuntimeException("Wrong password.");
        }
    }
    
//    public boolean validate(String token) {
//        
//        try {
//            Jws<Claims> claims = Jwts.parser()
//                    .verifyWith(key)
//                    .build()
//                    .parseSignedClaims(token);
//
//            Date expiryAt =  claims.getPayload().getExpiration();
//            Long userId = claims.getPayload().get("user_id", Long.class);
//
//        } catch (Exception e) {
//            return false;
//        }
//
//        return true;
//    }
    
    public boolean validate(String token) {
        try {
            
            JwtParser parser = Jwts.parser().setSigningKey(key);

            Jws<Claims> claims = parser.parseClaimsJws(token);

            Date expiryAt = claims.getBody().getExpiration();
            Long userId = claims.getBody().get("user_id", Long.class);

            return !expiryAt.before(new Date());
        } catch (Exception e) {
            // Any parsing or validation error means the token is invalid
            return false;
        }
    }
    // Generate a secure key

    private String createJwtToken(Long userId, String email) {
        Map<String, Object> dataInJwt = new HashMap<>();
        dataInJwt.put("user_id", userId);
        dataInJwt.put("email", email);

        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24); 
        Date expirationDate = calendar.getTime();

        String token = Jwts.builder()
                .setClaims(dataInJwt)
                .setIssuedAt(currentDate)
                .setSubject(email)
                .setExpiration(expirationDate) 
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        return token;
    }
//    private String createJwtToken(Long userId, String email) {
//        Map<String, Object> dataInJwt = new HashMap<>();
//        dataInJwt.put("user_id", userId);
//        dataInJwt.put("email", email);
//
//        Calendar calendar = Calendar.getInstance();
//        Date currentDate = calendar.getTime();
//
//
//        DefaultJwtBuilder defaultJwtBuilder= (DefaultJwtBuilder) Jwts.builder();
//    
//     
//     defaultJwtBuilder.setClaims(dataInJwt);
//     defaultJwtBuilder.setIssuedAt(new Date());
//     defaultJwtBuilder.signWith(SignatureAlgorithm.HS256, key);
//     String token  = defaultJwtBuilder.compact();
//     
//
//        return token;
//    }
}
