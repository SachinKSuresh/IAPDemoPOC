package com.IAPDemoPOC.Subscription.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "transactions")
public class Transaction extends IAPBaseEntity {

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "subscription_id")
	private Subscription subscription;

	private Double amount;
	private String currency;
	private String status; // e.g., successful, failed
	private String paymentProvider;
	private String providerTransactionId;

}
