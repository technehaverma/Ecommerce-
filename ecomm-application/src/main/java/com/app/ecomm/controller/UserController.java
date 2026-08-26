package com.app.ecomm.controller;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecomm.dto.UserResponse;
import com.app.ecomm.dto.UtilResponse;
import com.app.ecomm.entity.User;
import com.app.ecomm.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService service;

	@GetMapping("/user/users")
	public ResponseEntity<UtilResponse<List<UserResponse>>> getAllUsers() {
		UtilResponse<List<UserResponse>> response = new UtilResponse<>();
		List<UserResponse> userList = service.getAllUsers();
		if (userList != null) {
			response.setCode(HttpStatusCode.valueOf(200));
			response.setResponse(userList);
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
		} else {
			response.setCode(HttpStatusCode.valueOf(500));
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(500));
		}
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<UtilResponse<UserResponse>> getUserById(@PathVariable("id") Long id) {
		
		UtilResponse<UserResponse> response = new UtilResponse<>();
		UserResponse user = service.getUserById(id);
		if (user != null) {
			response.setCode(HttpStatusCode.valueOf(200));
			response.setResponse(user);
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
		} else {
			response.setCode(HttpStatusCode.valueOf(500));
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(500));
		}

	}

	@PostMapping("/user/users")
	public ResponseEntity<UtilResponse<String>> createUser(@RequestBody User user) {
		String resp = service.createUser(user);
		UtilResponse<String> response = new UtilResponse<>();
		if (resp != null && resp == "Success") {
			response.setCode(HttpStatusCode.valueOf(200));
			response.setResponse("Success");
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
		} else {
			response.setCode(HttpStatusCode.valueOf(500));
			response.setResponse("Failure");
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(500));
		}
	}

	@PutMapping("/user/users/{id}")
	public ResponseEntity<UtilResponse<String>> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
		String resp = service.updateUser(id, user);
		UtilResponse<String> response = new UtilResponse<>();

		if (resp != null && resp == "Success") {
			response.setCode(HttpStatusCode.valueOf(200));
			response.setResponse("Success");
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
		} else {
			response.setCode(HttpStatusCode.valueOf(500));
			response.setResponse("Failure");
			return new ResponseEntity<>(response, HttpStatusCode.valueOf(500));
		}
	}

}
