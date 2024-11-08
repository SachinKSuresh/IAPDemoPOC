package com.IAPDemoPOC.Subscription.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IAPDemoPOC.Subscription.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserName(String userName);
    Boolean existsByUserName(String userName);
    

    Optional<User> findByEmail(String email);
}

