package com.IAPDemoPOC.Subscription.Webhook.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.IAPDemoPOC.Subscription.Webhook.WebhookNotification;
import com.IAPDemoPOC.Subscription.Webhook.utility.ValidateSubscriptionNotifications;
import com.IAPDemoPOC.Subscription.models.Subscription;
import com.IAPDemoPOC.Subscription.service.SubscriptionService;

@Component
public class SubscriptionRenewedHandler implements WebhookNotificationHandler {
    private static final Logger logger = LoggerFactory.getLogger(SubscriptionRenewedHandler.class);
    
    @Autowired
    private SubscriptionService subscriptionService;
    
    @Override
    public void handle(WebhookNotification notification) {
        logger.info("Processing subscription renewal: {}", notification.getId());
        ValidateSubscriptionNotifications.validateSubscriptionDTO(notification);
        Subscription subscription = subscriptionService.getSubscriptionById(notification.getSubscriptionDTO().getId());
        subscription.setEndDate(subscription.getEndDate().plusDays(subscription.getSubscriptionProduct().getDuration()));
        subscriptionService.saveSubscription(subscription);
    }
}
