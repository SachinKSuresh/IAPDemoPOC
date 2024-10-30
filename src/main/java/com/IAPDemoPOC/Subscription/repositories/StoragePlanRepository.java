package com.IAPDemoPOC.Subscription.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IAPDemoPOC.Subscription.models.StoragePlan;

@Repository
public interface StoragePlanRepository extends JpaRepository<StoragePlan, Long> {
   
}
