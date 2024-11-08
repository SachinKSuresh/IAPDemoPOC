package com.IAPDemoPOC.Subscription.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "subscriptions")
public class Subscription extends IAPBaseEntity {

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private SubscriptionProduct subscriptionProduct;

	private String status; 
	private LocalDateTime startDate;
	private LocalDateTime endDate;

	private Boolean autoRenew;
	private String ppiStorageId;

}
