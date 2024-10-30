package com.IAPDemoPOC.Subscription.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IAPDemoPOC.Subscription.dtos.SubscriptionProductDTO;
import com.IAPDemoPOC.Subscription.models.StoragePlan;
import com.IAPDemoPOC.Subscription.models.SubscriptionProduct;
import com.IAPDemoPOC.Subscription.repositories.StoragePlanRepository;
import com.IAPDemoPOC.Subscription.repositories.SubscriptionProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SubscriptionProductServiceImpl implements SubscriptionProductService {

	@Autowired
	private SubscriptionProductRepository productRepository;

	@Autowired
	private StoragePlanRepository planRepository;

	@Override
	public List<SubscriptionProduct> getAllProducts() {
		
		return productRepository.findAll();//.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public SubscriptionProduct getProductById(Long id) {
		return productRepository.findById(id)
//				.map(this::convertToDTO)
				.orElseThrow(() -> new RuntimeException("Product not found"));
	}

	@Override
	public SubscriptionProduct createProduct(SubscriptionProduct product) {
		return productRepository.save(product);
	}

	@Override
	public SubscriptionProduct updateProduct(Long id, SubscriptionProductDTO productDTO) {
		SubscriptionProduct product = productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Product not found"));
//		plan not to be updated
		product.setDuration(productDTO.getDuration());
		product.setPrice(productDTO.getPrice());
		product.setCurrency(productDTO.getCurrency());
		product.setAppStoreProductId(productDTO.getAppStoreProductId());
		product.setPlayStoreProductId(productDTO.getPlayStoreProductId());
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

	

}
