package com.IAPDemoPOC.Subscription.dtos;

import lombok.Data;

@Data
public class StoragePlanDTO {
	private Long id;
	private String name;
	private Long storageSize; // in bytes or GB
}
