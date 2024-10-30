package com.IAPDemoPOC.Subscription.helper;

import com.IAPDemoPOC.Subscription.dtos.UserDTO;
import com.IAPDemoPOC.Subscription.models.User;

import lombok.experimental.UtilityClass;


@UtilityClass
public class UserEntityUtil {

	 public UserDTO convertToDTO(User user) {
	        UserDTO dto = new UserDTO();
	        dto.setId(user.getId());
	        dto.setEmail(user.getEmail());
	        dto.setName(user.getName());
	        dto.setLastLogin(user.getLastLogin());
	        return dto;
	    }

	    public User convertToEntity(UserDTO dto) {
	        User user = new User();
	        user.setEmail(dto.getEmail());
	        user.setName(dto.getName());
	        return user;
	    }
}
