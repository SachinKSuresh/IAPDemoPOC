package com.IAPDemoPOC.Subscription.auth;

import lombok.Data;

@Data
public class SignupRequestDTO {

	private String email;
	private String password;
	private String userName;
}
