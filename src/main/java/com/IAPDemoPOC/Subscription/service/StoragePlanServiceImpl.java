package com.IAPDemoPOC.Subscription.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IAPDemoPOC.Subscription.dtos.StoragePlanDTO;
import com.IAPDemoPOC.Subscription.models.StoragePlan;
import com.IAPDemoPOC.Subscription.repositories.StoragePlanRepository;

@Service
public class StoragePlanServiceImpl implements StoragePlanService {
	private static final Logger log = LoggerFactory.getLogger(StoragePlanServiceImpl.class);
    
    @Autowired
    private StoragePlanRepository planRepository;

    @Override
    public List<StoragePlan> getAllPlans() {
        return planRepository.findAll();
    }

    @Override
    public StoragePlan getPlanById(Long id) {
        return planRepository.findById(id)
//                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
    }

    @Override
    public StoragePlan createPlan(StoragePlan plan) {
//        StoragePlan plan = convertToEntity(planDTO);
        return planRepository.save(plan);
    }

    @Override
    public StoragePlan updatePlan(Long id, StoragePlanDTO planDTO) {
        StoragePlan plan = planRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plan not found"));
        plan.setName(planDTO.getName());
        plan.setStorageSize(planDTO.getStorageSize());
        return planRepository.save(plan);
    }

    @Override
    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }

    
}

