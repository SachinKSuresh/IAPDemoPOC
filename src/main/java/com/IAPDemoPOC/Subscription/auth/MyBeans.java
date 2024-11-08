package com.IAPDemoPOC.Subscription.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class MyBeans {
	 @Bean
	    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
