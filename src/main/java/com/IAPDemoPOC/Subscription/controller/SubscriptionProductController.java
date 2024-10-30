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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IAPDemoPOC.Subscription.dtos.SubscriptionProductDTO;
import com.IAPDemoPOC.Subscription.helper.ProductEntityHelper;
import com.IAPDemoPOC.Subscription.models.StoragePlan;
import com.IAPDemoPOC.Subscription.models.SubscriptionProduct;
import com.IAPDemoPOC.Subscription.service.StoragePlanService;
import com.IAPDemoPOC.Subscription.service.SubscriptionProductService;

@RestController
@RequestMapping("/api/products")
public class SubscriptionProductController {

	@Autowired
	private SubscriptionProductService productService;

	@Autowired
	private StoragePlanService storagePlanService;

	@GetMapping
	public List<SubscriptionProductDTO> getAllProducts() {
		return productService.getAllProducts().stream().map(proDto -> ProductEntityHelper.convertToDTO(proDto))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubscriptionProductDTO> getProductById(@PathVariable Long id) {
		return ResponseEntity.ok(ProductEntityHelper.convertToDTO(productService.getProductById(id)));
	}

	@PostMapping
	public ResponseEntity<SubscriptionProductDTO> createProduct(@RequestBody SubscriptionProductDTO productDTO) {
		StoragePlan plan = storagePlanService.getPlanById(productDTO.getPlanId());

		return ResponseEntity.status(201).body(ProductEntityHelper
				.convertToDTO(productService.createProduct(ProductEntityHelper.convertToEntity(productDTO, plan))));
	}

	@PutMapping("/{id}")
	public ResponseEntity<SubscriptionProductDTO> updateProduct(@PathVariable Long id,
			@RequestBody SubscriptionProductDTO productDTO) {
		return ResponseEntity.ok(ProductEntityHelper
				.convertToDTO(productService.updateProduct(id, productDTO)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
}
