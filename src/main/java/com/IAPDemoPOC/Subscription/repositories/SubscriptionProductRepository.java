package com.IAPDemoPOC.Subscription.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IAPDemoPOC.Subscription.models.SubscriptionProduct;

@Repository
public interface SubscriptionProductRepository extends JpaRepository<SubscriptionProduct, Long> {
    
}
