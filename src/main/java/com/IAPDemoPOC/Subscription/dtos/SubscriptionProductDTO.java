package com.IAPDemoPOC.Subscription.dtos;

import lombok.Data;

@Data
public class SubscriptionProductDTO {
	private Long id;
	private Long planId;
	private String name;
	private Integer duration; // in days or months
	private Double price;
	private String currency;
	private String appStoreProductId;
	private String playStoreProductId;
}
