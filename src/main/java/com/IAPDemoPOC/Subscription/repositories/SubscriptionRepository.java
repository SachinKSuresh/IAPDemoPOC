package com.IAPDemoPOC.Subscription.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IAPDemoPOC.Subscription.models.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{

}
