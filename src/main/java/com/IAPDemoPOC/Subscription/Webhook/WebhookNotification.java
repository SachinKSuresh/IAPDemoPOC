package com.IAPDemoPOC.Subscription.Webhook;

import com.IAPDemoPOC.Subscription.dtos.SubscriptionDTO;

import lombok.Data;


@Data
public class WebhookNotification {
	//trx ID 
	private String id;
	private String event;
	private String message;
	private SubscriptionDTO subscriptionDTO;
	private NotificationType type;

	public enum NotificationType {
		SUBSCRIPTION_CREATED("Subscription Created"),
		SUBSCRIPTION_RENEWED("Subscription Renewed"),
		SUBSCRIPTION_CANCELLED("Subscription Cancelled");

		private final String displayName;

		NotificationType(String displayName) {
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}

	}
}
