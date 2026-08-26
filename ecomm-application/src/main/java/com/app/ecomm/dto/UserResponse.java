package com.app.ecomm.dto;

import com.app.ecomm.enums.UserRole;

import lombok.Data;

@Data
public class UserResponse {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private UserRole role = UserRole.USER;
	private AddressResponse address;

}
