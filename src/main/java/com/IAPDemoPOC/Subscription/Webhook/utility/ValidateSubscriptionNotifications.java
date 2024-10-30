package com.IAPDemoPOC.Subscription.Webhook.utility;

import org.springframework.beans.factory.annotation.Autowired;

import com.IAPDemoPOC.Subscription.Webhook.WebhookNotification;
import com.IAPDemoPOC.Subscription.models.Subscription;
import com.IAPDemoPOC.Subscription.models.SubscriptionProduct;
import com.IAPDemoPOC.Subscription.models.User;
import com.IAPDemoPOC.Subscription.repositories.SubscriptionProductRepository;
import com.IAPDemoPOC.Subscription.repositories.SubscriptionRepository;
import com.IAPDemoPOC.Subscription.repositories.UserRepository;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidateSubscriptionNotifications {

	@Autowired
	private UserRepository userRepository;
	@Autowired 
	private SubscriptionProductRepository subscriptionProductRepository;
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	public void validateSubscriptionDTO(WebhookNotification notification) {
		if (notification.getSubscriptionDTO() == null) {
			throw new RuntimeException("Subscription Deatils not found");
		}
		// Validate Token
		// ValidateFactory for appStore, PlayStore , webPurchase and validate
		
		if (notification.getSubscriptionDTO().getToken() == null) {
			throw new RuntimeException("Invalid Purchase");
		}

	}
	public User validateUserId(long userID) {
		return userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));//.orElseThrow(() -> new RuntimeException("User not found"));
	}
	public SubscriptionProduct validateProductId(long productID) {
		return subscriptionProductRepository.findById(productID).orElseThrow(() -> new RuntimeException("Product not found"));
	}
	public Subscription validateSubscriptionID(long subscriptionID) {
		return subscriptionRepository.findById(subscriptionID).orElseThrow(() -> new RuntimeException("Subscription not found"));
	}

}
