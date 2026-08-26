package com.ecommerce.user.dto;

import com.ecommerce.user.entity.UserRole;

import lombok.Data;

@Data
public class UserRequest {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private UserRole role = UserRole.USER;
	private AddressRequest address;

}
