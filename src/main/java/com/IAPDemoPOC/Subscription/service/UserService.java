package com.IAPDemoPOC.Subscription.service;

import java.util.List;

import com.IAPDemoPOC.Subscription.dtos.UserDTO;
import com.IAPDemoPOC.Subscription.models.User;

public interface UserService {
	List<User> getAllUsers();

	User getUserById(Long id);

	User createUser(User user);

	User updateUser(Long id, UserDTO user);

	void deleteUser(Long id);
}
