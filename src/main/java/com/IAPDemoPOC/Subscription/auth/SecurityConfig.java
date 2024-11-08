package com.IAPDemoPOC.Subscription.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
public class SecurityConfig {
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests(requests -> requests.anyRequest().permitAll()).csrf((c) -> c.disable());
        
        return http.build();
    }
	
//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()
//            .authorizeRequests()
//                .requestMatchers("/auth/**").permitAll() 
//                .anyRequest().authenticated() 
//            .and()
//            .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
//        // If you have a JWT filter, it should be added here
//        // .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
	
//	 @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        http
//	            .csrf().disable()
//	            .authorizeRequests()
//	                .requestMatchers("/api/products").authenticated() // Ensure /api/products requires authentication
//	                .anyRequest().permitAll()
//	            .and()
//	            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Use stateless session for JWT
//	            .and()
//	            .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//
//	        return http.build();
//	    }
}
