package com.ecommerce.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import com.ecommerce.user.dto.UserInfo;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.entity.User;
import com.ecommerce.user.mapper.UserMapper;
import com.ecommerce.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@RefreshScope
public class UserServiceImpl implements UserService {

	@Autowired
	private final UserRepository repository;
	@Autowired
	private final UserMapper userMapper;

	@Value("${users.name:dev}")
	private String projectName;
	@Override
	public List<UserResponse> getAllUsers() {
		
		List<User> user = repository.findAll();
		System.out.println(projectName);
		var userResponseList = new ArrayList<UserResponse>(); 
		if(user!=null && user.size()>0) {
			userMapper.convertUserListToUserResponse(user, userResponseList);
		}
		return userResponseList;
	}

	@Override
	public String createUser(User user) {
		repository.save(user);
		return "Success";
	}

	@Override
	public UserResponse getUserById(String id) {
		UserResponse userResponse = new UserResponse();
		Optional<User> user = repository.findById(id);
		if(user.isPresent()) {
			userMapper.convertUserToUserResponse(user.get(), userResponse);
		}
		return userResponse;
	}

	@Override
	public String updateUser(String id, User user) {

		Optional<User> exUser = repository.findById(id);
		if (exUser.isPresent()) {
			User x = exUser.get();
			x.setFirstName(user.getFirstName().get());
			x.setLastName(user.getLastName().get());
			x.setPhoneNumber(user.getPhoneNumber().get());
			x.setEmail(user.getEmail().get());
			x.setRole(user.getRole());
			repository.save(x);
			return "Success";
		} else {
			return "Failure";
		}

	}

}
