package com.IAPDemoPOC.Subscription.helper;

import com.IAPDemoPOC.Subscription.dtos.StoragePlanDTO;
import com.IAPDemoPOC.Subscription.models.StoragePlan;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StoragePlanEntityHelper {

	public StoragePlanDTO convertToDTO(StoragePlan plan) {
        StoragePlanDTO dto = new StoragePlanDTO();
        dto.setId(plan.getId());
        dto.setName(plan.getName());
        dto.setStorageSize(plan.getStorageSize());
        return dto;
    }

    public StoragePlan convertToEntity(StoragePlanDTO dto) {
        StoragePlan plan = new StoragePlan();
        plan.setName(dto.getName());
        plan.setStorageSize(dto.getStorageSize());
        return plan;
    }
}
