package com.IAPDemoPOC.Subscription.Webhook.handler;

import com.IAPDemoPOC.Subscription.Webhook.WebhookNotification;

public interface WebhookNotificationHandler {
    void handle(WebhookNotification notification);
}

