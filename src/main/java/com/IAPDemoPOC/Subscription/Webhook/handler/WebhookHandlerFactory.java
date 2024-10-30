package com.IAPDemoPOC.Subscription.Webhook.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.IAPDemoPOC.Subscription.Webhook.WebhookNotification.NotificationType;

@Component
public class WebhookHandlerFactory {
    private final Map<NotificationType, WebhookNotificationHandler> handlers = new HashMap<>();

    @Autowired
    public WebhookHandlerFactory(
        SubscriptionCreatedHandler createdHandler,
        SubscriptionRenewedHandler renewedHandler,
        SubscriptionCancelledHandler cancelledHandler
    ) {
        handlers.put(NotificationType.SUBSCRIPTION_CREATED, createdHandler);
        handlers.put(NotificationType.SUBSCRIPTION_RENEWED, renewedHandler);
        handlers.put(NotificationType.SUBSCRIPTION_CANCELLED, cancelledHandler);
    }

    public WebhookNotificationHandler getHandler(NotificationType type) {
        WebhookNotificationHandler handler = handlers.get(type);
        if (handler == null) {
            throw new IllegalArgumentException("No handler found for notification type: " + type);
        }
        return handler;
    }
}