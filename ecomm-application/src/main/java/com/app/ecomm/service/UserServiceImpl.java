package com.app.ecomm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.ecomm.dto.UserResponse;
import com.app.ecomm.entity.User;
import com.app.ecomm.mapper.UserMapper;
import com.app.ecomm.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	private final UserRepository repository;
	@Autowired
	private final UserMapper userMapper;

	@Override
	public List<UserResponse> getAllUsers() {
		
		List<User> user = repository.findAll();
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
	public UserResponse getUserById(Long id) {
		UserResponse userResponse = new UserResponse();
		Optional<User> user = repository.findById(id);
		if(user.isPresent()) {
			userMapper.convertUserToUserResponse(user.get(), userResponse);
		}
		return userResponse;
	}

	@Override
	public String updateUser(Long id, User user) {

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
