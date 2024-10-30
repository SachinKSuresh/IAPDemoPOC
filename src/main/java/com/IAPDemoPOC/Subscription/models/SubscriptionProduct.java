package com.IAPDemoPOC.Subscription.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "subscription_products")
public class SubscriptionProduct extends IAPBaseEntity {

	@ManyToOne
	@JoinColumn(name = "plan_id")
	private StoragePlan storagePlan;
	private String name;
	private Integer duration; // in days
	private Double price;
	private String currency;
	private String appStoreProductId;
	private String playStoreProductId;
}
