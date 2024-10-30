package com.IAPDemoPOC.Subscription.service;

import java.util.List;

import com.IAPDemoPOC.Subscription.dtos.StoragePlanDTO;
import com.IAPDemoPOC.Subscription.models.StoragePlan;

public interface StoragePlanService {
	List<StoragePlan> getAllPlans();

	StoragePlan getPlanById(Long id);

	StoragePlan createPlan(StoragePlan planDTO);

	StoragePlan updatePlan(Long id, StoragePlanDTO planDTO);

	void deletePlan(Long id);
}
