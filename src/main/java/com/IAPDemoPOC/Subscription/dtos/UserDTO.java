package com.IAPDemoPOC.Subscription.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserDTO {
	private Long id;
	private String email;
	private String name;
	private LocalDateTime lastLogin;
}
