package com.app.ecomm.service;

import java.util.List;

import com.app.ecomm.dto.UserResponse;
import com.app.ecomm.entity.User;

public interface UserService {
	public List<UserResponse> getAllUsers();

	public String createUser(User user);

	public UserResponse getUserById(Long id);

	public String updateUser(Long id, User user);
}
