package com.IAPDemoPOC.Subscription.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() 
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
            .and()
            .authorizeRequests()
            .requestMatchers("/auth/login", "/auth/register","/swagger-resources/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v2/api-docs",
                    "/v3/api-docs",
                    "/webjars/**").permitAll() 
            .anyRequest().authenticated(); 

      
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
    }
}
























//import org.springframework.context.annotation.Bean;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//@Configuration
//public class SecurityConfig {
//	
//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.authorizeRequests(requests -> requests.anyRequest().permitAll()).csrf((c) -> c.disable());
//        
//        return http.build();
//    }
////	@Override
////	protected void configure(HttpSecurity http) throws Exception {
////	    http
////	        .csrf().disable()
////	        .authorizeRequests()
////	        .requestMatchers("/public/**", "/auth/login", "/auth/register").permitAll()  // Whitelist public endpoints
////	        .anyRequest().authenticated()  // Require authentication for other requests
////	        .and()
////	        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter before UsernamePassword filter
////	}
////	@Bean
////    public SecurityFilterChain securityFilterChain1(HttpSecurity http) throws Exception {
////        http
////            .csrf().disable()
////            .authorizeRequests()
////                .requestMatchers("/auth/**").permitAll() 
////                .anyRequest().
////                authenticated() 
////            .and()
////            .sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
////        // If you have a JWT filter, it should be added here
////        // .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
////
////        return http.build();
////    }
//	
////	 @Bean
////	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////	        http
////	            .csrf().disable()
////	            .authorizeRequests()
////	                .requestMatchers("/api/products").authenticated() // Ensure /api/products requires authentication
////	                .anyRequest().permitAll()
////	            .and()
////	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Use stateless session for JWT
////	            .and()
////	            .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
////
////	        return http.build();
////	    }
//}
