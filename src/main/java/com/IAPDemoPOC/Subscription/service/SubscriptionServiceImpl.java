package com.IAPDemoPOC.Subscription.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IAPDemoPOC.Subscription.dtos.SubscriptionDTO;
import com.IAPDemoPOC.Subscription.models.Subscription;
import com.IAPDemoPOC.Subscription.models.SubscriptionProduct;
import com.IAPDemoPOC.Subscription.models.User;
import com.IAPDemoPOC.Subscription.repositories.SubscriptionProductRepository;
import com.IAPDemoPOC.Subscription.repositories.SubscriptionRepository;
import com.IAPDemoPOC.Subscription.repositories.UserRepository;
//TODO : don't introduce DTO into Service ( Decouple from DTO and Repository use service )


@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Override
	public List<Subscription> getAllSubscription() {
		return subscriptionRepository.findAll();
	}

	@Override
	public List<Subscription> getAllUserSubscription(Long id) {
		return subscriptionRepository.findAll();
	}

	@Override
	public Subscription getSubscriptionById(Long id) {
		return subscriptionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Subscription not found"));
	}

	@Override
	public Subscription saveSubscription(Subscription subscription) {
		//allocate storage API business flow
		return subscriptionRepository.save(subscription);

	}


//	@Override
//	public SubscriptionDTO updateSubscription(Long id, SubscriptionDTO subscriptionDTO) {
//		Subscription subscription = subscriptionRepository.findById(id)
//				.orElseThrow(() -> new RuntimeException("Subscription not found"));
//		// update details
//		return convertToDTO(subscriptionRepository.save(subscription));
//
//	}

	@Override
	public void deleteSubscription(Long id) {
		subscriptionRepository.deleteById(id);

	}

	

}
