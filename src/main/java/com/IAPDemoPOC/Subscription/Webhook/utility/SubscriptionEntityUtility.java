package com.IAPDemoPOC.Subscription.Webhook.utility;

import org.springframework.beans.factory.annotation.Autowired;

import com.IAPDemoPOC.Subscription.dtos.SubscriptionDTO;
import com.IAPDemoPOC.Subscription.models.Subscription;
import com.IAPDemoPOC.Subscription.models.SubscriptionProduct;
import com.IAPDemoPOC.Subscription.models.User;
import com.IAPDemoPOC.Subscription.service.UserService;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SubscriptionEntityUtility {
	
	@Autowired
	private UserService userService;
	
	public Subscription convertToEntity(SubscriptionDTO subscriptionDTO,User user,SubscriptionProduct product) {
		Subscription subscription = new Subscription();
		subscription.setUser(user);
		subscription.setSubscriptionProduct(product);
		subscription.setStatus(subscriptionDTO.getStatus());
		subscription.setStartDate(subscriptionDTO.getStartDate());
		subscription.setEndDate(subscriptionDTO.getStartDate().plusDays(product.getDuration()));
		subscription.setAutoRenew(subscriptionDTO.getAutoRenew());
		return subscription;
	}
	
	
	public SubscriptionDTO convertToDTO(Subscription subscription) {
		SubscriptionDTO dto = new SubscriptionDTO();
		dto.setId(subscription.getId());
		dto.setUserId(subscription.getUser().getId());
		dto.setProductId(subscription.getSubscriptionProduct().getId());
		dto.setStatus(subscription.getStatus());
		dto.setStartDate(subscription.getStartDate());
		dto.setEndDate(subscription.getEndDate());
		dto.setAutoRenew(subscription.getAutoRenew());
		return dto;
	}

}
