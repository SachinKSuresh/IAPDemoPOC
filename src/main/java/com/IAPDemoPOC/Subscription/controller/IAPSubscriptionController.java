package com.IAPDemoPOC.Subscription.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IAPDemoPOC.Subscription.Webhook.utility.SubscriptionEntityUtility;
import com.IAPDemoPOC.Subscription.dtos.SubscriptionDTO;
import com.IAPDemoPOC.Subscription.service.SubscriptionService;

//Create/Read/Update/Delete subscription
//Get subscription status
//List active subscriptions
//Basic purchase validation endpoint

@RestController
@RequestMapping("/api/subscriptions")
public class IAPSubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;

	@GetMapping
	public List<SubscriptionDTO> getAllSubscription() {
		return subscriptionService.getAllSubscription().stream().map(sub-> SubscriptionEntityUtility.convertToDTO(sub)).collect(Collectors.toList());
	}

	@GetMapping("/{userId}")
	public ResponseEntity<List<SubscriptionDTO>> getAllSubscription(@PathVariable Long userId) {
		return ResponseEntity.ok(subscriptionService.getAllUserSubscription(userId).stream().map(sub-> SubscriptionEntityUtility.convertToDTO(sub)).collect(Collectors.toList()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubscriptionDTO> getSubscriptionById(@PathVariable Long id) {
		return ResponseEntity.ok(SubscriptionEntityUtility.convertToDTO(subscriptionService.getSubscriptionById(id)));
	}

	@GetMapping("/active")
	public ResponseEntity<List<SubscriptionDTO>> getActiveSubscription() {
		return ResponseEntity.ok(subscriptionService.getActiveSubscription().stream().map(sub-> SubscriptionEntityUtility.convertToDTO(sub)).collect(Collectors.toList()));
	}
	
	
	
//	@PostMapping
//	public ResponseEntity<SubscriptionDTO> createSubscription(SubscriptionDTO subscriptionDTO) {
//		return ResponseEntity.status(201).body(subscriptionService.createSubscription(subscriptionDTO));
//	}

//	@PutMapping("/{id}")
//	public ResponseEntity<SubscriptionDTO> updateSubscription(Long id, SubscriptionDTO subscriptionDTO) {
//		return ResponseEntity.status(201).body(subscriptionService.updateSubscription(id, subscriptionDTO));
//	}

 
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
		subscriptionService.deleteSubscription(id);
		return ResponseEntity.noContent().build();
	}


}
