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

import com.IAPDemoPOC.Subscription.dtos.StoragePlanDTO;
import com.IAPDemoPOC.Subscription.helper.StoragePlanEntityHelper;
import com.IAPDemoPOC.Subscription.models.StoragePlan;
import com.IAPDemoPOC.Subscription.service.StoragePlanService;

@RestController
@RequestMapping("/api/plans")
public class StoragePlanController {

	@Autowired
	private StoragePlanService planService;

	@GetMapping
	public List<StoragePlanDTO> getAllPlans() {
		return planService.getAllPlans().stream()
              .map(plan->StoragePlanEntityHelper.convertToDTO(plan))
              .collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<StoragePlanDTO> getPlanById(@PathVariable Long id) {
		return ResponseEntity.ok(StoragePlanEntityHelper.convertToDTO(planService.getPlanById(id)));
	}

	@PostMapping
	public ResponseEntity<StoragePlanDTO> createPlan(@RequestBody StoragePlanDTO planDTO) {
		 StoragePlan plan = StoragePlanEntityHelper.convertToEntity(planDTO);
		return ResponseEntity.status(201).body(StoragePlanEntityHelper.convertToDTO(planService.createPlan(plan)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<StoragePlanDTO> updatePlan(@PathVariable Long id, @RequestBody StoragePlanDTO planDTO) {
		return ResponseEntity.ok(StoragePlanEntityHelper.convertToDTO(planService.updatePlan(id, planDTO)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
		planService.deletePlan(id);
		return ResponseEntity.noContent().build();
	}
}
