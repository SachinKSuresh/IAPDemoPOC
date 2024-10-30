package com.IAPDemoPOC.Subscription.service;

import java.util.List;

import com.IAPDemoPOC.Subscription.dtos.SubscriptionDTO;
import com.IAPDemoPOC.Subscription.models.Subscription;


//mostly should be made private
public interface SubscriptionService {
	
	List<Subscription> getAllSubscription();

	List<Subscription> getAllUserSubscription(Long id);
	
	Subscription getSubscriptionById(Long id);

	Subscription saveSubscription(Subscription subscription);

//	SubscriptionDTO updateSubscription(Long id, SubscriptionDTO subscriptionDTO);
	
//	Subscription renewSubscription(Long id, Subscription subscription);

	void deleteSubscription(Long id);

}
