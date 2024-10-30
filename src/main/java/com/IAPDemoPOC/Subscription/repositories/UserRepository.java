package com.IAPDemoPOC.Subscription.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IAPDemoPOC.Subscription.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   
}

