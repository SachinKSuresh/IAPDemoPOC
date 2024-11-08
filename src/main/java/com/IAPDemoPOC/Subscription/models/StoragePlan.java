package com.IAPDemoPOC.Subscription.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "storage_plans")
public class StoragePlan extends IAPBaseEntity {

	private String name;
	private Long storageSize; // in bytes 

}
