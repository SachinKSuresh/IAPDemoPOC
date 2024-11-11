package com.IAPDemoPOC.Subscription.Webhook.utility;

import com.IAPDemoPOC.Subscription.models.Subscription;
import com.IAPDemoPOC.Subscription.models.Transaction;
import com.IAPDemoPOC.Subscription.models.User;

import lombok.experimental.UtilityClass;


@UtilityClass
public class TransactionEntityUtility {

	public Transaction convertToEntity(User user, Subscription subscription, String status, Double amount,
			String currency) {
		Transaction transaction = new Transaction();
		transaction.setUser(user);
		transaction.setSubscription(subscription);
		transaction.setAmount(amount);
		transaction.setCurrency(currency);
		transaction.setStatus(status);

		return transaction;
	}
}
