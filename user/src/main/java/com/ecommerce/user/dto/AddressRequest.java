package com.ecommerce.user.dto;

import lombok.Data;

@Data
public class AddressRequest {
	private Long id;
	private String addressLine1;
	private String addressLine2;
	private String landmark;
	private String city;
	private String state;
	private String country;
	private String zipCode;
}
