package com.IAPDemoPOC.Subscription.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.IAPDemoPOC.Subscription.models.Subscription;
import com.IAPDemoPOC.Subscription.models.User;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long>{

	
	
	// Find subscriptions by status
    List<Subscription> findByStatus(String status);

    // Find subscriptions by user
    List<Subscription> findByUser(User user);

    // Find subscriptions by status and user
    List<Subscription> findByStatusAndUser(String status, User user);

    // Custom query to find active subscriptions for a user with specific status
    @Query("SELECT s FROM Subscription s WHERE s.status = :status AND s.user = :user AND s.endDate > CURRENT_TIMESTAMP")
    List<Subscription> findActiveSubscriptionsByStatusAndUser(@Param("status") String status, @Param("user") User user);

}
