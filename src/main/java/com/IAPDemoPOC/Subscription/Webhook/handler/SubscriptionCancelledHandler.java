package com.IAPDemoPOC.Subscription.Webhook.handler;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.IAPDemoPOC.Subscription.Webhook.WebhookNotification;
import com.IAPDemoPOC.Subscription.Webhook.utility.ValidateSubscriptionNotifications;
import com.IAPDemoPOC.Subscription.models.Subscription;
import com.IAPDemoPOC.Subscription.service.SubscriptionService;

@Component
public class SubscriptionCancelledHandler implements WebhookNotificationHandler {
    private static final Logger logger = LoggerFactory.getLogger(SubscriptionCancelledHandler.class);
    
    
    @Autowired
    private SubscriptionService subscriptionService;
    @Override
    public void handle(WebhookNotification notification) {
        logger.info("Processing subscription cancellation: {}", notification.getId());
        ValidateSubscriptionNotifications.validateSubscriptionDTO(notification);
        Subscription subscription = subscriptionService.getSubscriptionById(notification.getSubscriptionDTO().getId());
        
        //Subscription subscription = ValidateSubscriptionNotifications.validateSubscriptionID(notification.getSubscriptionDTO().getId());
        subscription.setEndDate(LocalDateTime.now());
        subscription.setIsActive(false);
        subscriptionService.saveSubscription(subscription);
    }
}
