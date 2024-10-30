package com.IAPDemoPOC.Subscription.service;

import java.util.List;

import com.IAPDemoPOC.Subscription.dtos.SubscriptionProductDTO;
import com.IAPDemoPOC.Subscription.models.SubscriptionProduct;

public interface SubscriptionProductService {
	List<SubscriptionProduct> getAllProducts();

	SubscriptionProduct getProductById(Long id);

	SubscriptionProduct createProduct(SubscriptionProduct productDTO);

	SubscriptionProduct updateProduct(Long id, SubscriptionProductDTO productDTO);

	void deleteProduct(Long id);
}
