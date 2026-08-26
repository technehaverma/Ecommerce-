package com.ecommerce.user.service;

import java.util.List;

import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.entity.User;

public interface UserService {
	public List<UserResponse> getAllUsers();

	public String createUser(User user);

	public UserResponse getUserById(String id);

	public String updateUser(String id, User user);
}
