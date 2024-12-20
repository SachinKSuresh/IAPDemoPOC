package com.IAPDemoPOC.Subscription.Webhook.handler;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.IAPDemoPOC.Subscription.Webhook.WebhookNotification;
import com.IAPDemoPOC.Subscription.Webhook.utility.SubscriptionEntityUtility;
import com.IAPDemoPOC.Subscription.Webhook.utility.TransactionEntityUtility;
import com.IAPDemoPOC.Subscription.Webhook.utility.ValidateSubscriptionNotifications;
import com.IAPDemoPOC.Subscription.dtos.SubscriptionDTO;
import com.IAPDemoPOC.Subscription.models.Subscription;
import com.IAPDemoPOC.Subscription.models.SubscriptionProduct;
import com.IAPDemoPOC.Subscription.models.Transaction;
import com.IAPDemoPOC.Subscription.models.User;
import com.IAPDemoPOC.Subscription.service.SubscriptionProductService;
import com.IAPDemoPOC.Subscription.service.SubscriptionService;
import com.IAPDemoPOC.Subscription.service.TransactionService;
import com.IAPDemoPOC.Subscription.service.UserService;

@Component
public class SubscriptionCreatedHandler implements WebhookNotificationHandler {
	private static final Logger logger = LoggerFactory.getLogger(SubscriptionCreatedHandler.class);

	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private UserService userService;
	@Autowired
	private SubscriptionProductService productService;

	@Autowired
	private TransactionService transactionService;

	@Override
	public void handle(WebhookNotification notification) {
			logger.info("Processing subscription creation: {}", notification.getId());
			// validate all the data
			ValidateSubscriptionNotifications.validateSubscriptionDTO(notification);
			// need to avoid multiple DB calls
			SubscriptionDTO subscriptionDTO = notification.getSubscriptionDTO();
//        validate purchase 
			User user = userService.getUserById(subscriptionDTO.getUserId());
			SubscriptionProduct product = productService.getProductById(subscriptionDTO.getProductId());
			subscriptionDTO.setStartDate(LocalDateTime.now());
			subscriptionDTO.setStatus("Active");

			Subscription subscription = SubscriptionEntityUtility.convertToEntity(subscriptionDTO, user, product);
			subscriptionService.saveSubscription(subscription);

			transactionService.saveTransaction(TransactionEntityUtility.convertToEntity(user, subscription, subscriptionDTO.getStatus(),
					subscriptionDTO.getAmount()==null ? 0 : subscriptionDTO.getAmount(), subscriptionDTO.getCurrency()==null ? "undifined" : subscriptionDTO.getCurrency()));

	}

}
