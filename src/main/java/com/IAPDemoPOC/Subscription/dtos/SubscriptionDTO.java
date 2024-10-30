package com.IAPDemoPOC.Subscription.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class SubscriptionDTO {
	
	private Long id;
	private Long userId;
	private Long productId;
	private String status;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Boolean autoRenew;
	private String token;
}
