package com.IAPDemoPOC.Subscription.helper;

import com.IAPDemoPOC.Subscription.dtos.SubscriptionProductDTO;
import com.IAPDemoPOC.Subscription.models.StoragePlan;
import com.IAPDemoPOC.Subscription.models.SubscriptionProduct;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductEntityHelper {
	
	public SubscriptionProduct convertToEntity(SubscriptionProductDTO dto,StoragePlan plan) {
		SubscriptionProduct product = new SubscriptionProduct();
//		StoragePlan plan = planRepository.findById(dto.getPlanId())
//				.orElseThrow(() -> new RuntimeException("Plan not found"));
		product.setName(dto.getName());
		product.setStoragePlan(plan);
		product.setDuration(dto.getDuration());
		product.setPrice(dto.getPrice());
		product.setCurrency(dto.getCurrency());
		product.setAppStoreProductId(dto.getAppStoreProductId());
		product.setPlayStoreProductId(dto.getPlayStoreProductId());
		return product;
	}
	
	public SubscriptionProductDTO convertToDTO(SubscriptionProduct product) {
		SubscriptionProductDTO dto = new SubscriptionProductDTO();
		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setPlanId(product.getStoragePlan().getId());
		dto.setDuration(product.getDuration());
		dto.setPrice(product.getPrice());
		dto.setCurrency(product.getCurrency());
		dto.setAppStoreProductId(product.getAppStoreProductId());
		dto.setPlayStoreProductId(product.getPlayStoreProductId());
		return dto;
	}

}
