package com.IAPDemoPOC.Subscription.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "users")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends IAPBaseEntity {

	@Column(nullable = false, unique = true)
	private String email;

//	@Column(nullable = false, unique = true)
	private String userName;

	@Column(name = "last_login")
	private LocalDateTime lastLogin;

  @Column(nullable = false)
  private String password;
  
  private String role;
}
