package com.IAPDemoPOC.Subscription.controller;

import java.util.List;

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

import com.IAPDemoPOC.Subscription.dtos.UserDTO;
import com.IAPDemoPOC.Subscription.helper.UserEntityUtil;
import com.IAPDemoPOC.Subscription.models.User;
import com.IAPDemoPOC.Subscription.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers().stream().map(user->UserEntityUtil.convertToDTO(user)).toList();
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
		return ResponseEntity.ok(UserEntityUtil.convertToDTO(userService.getUserById(id)));
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		User user = UserEntityUtil.convertToEntity(userDTO);
		
		return ResponseEntity.status(201).body(UserEntityUtil.convertToDTO(userService.createUser(user)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		return ResponseEntity.ok(UserEntityUtil.convertToDTO(userService.updateUser(id, userDTO)));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
