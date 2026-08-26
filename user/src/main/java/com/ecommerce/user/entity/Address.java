package com.ecommerce.user.entity;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Address {
	
	private String id;
	private String addressLine1;
	private String addressLine2;
	private String landmark;
	private String city;
	private String state;
	private String country;
	private String zipCode;

	
	public Optional<String> getAddressLine1() {
		return Optional.ofNullable(addressLine1);
	}

	public Optional<String> getAddressLine2() {
		return Optional.ofNullable(addressLine2);
	}

	public Optional<String> getLandmark() {
		return Optional.ofNullable(landmark);
	}

	public Optional<String> getCity() {
		return Optional.ofNullable(city);
	}

	public Optional<String> getState() {
		return Optional.ofNullable(state);
	}

	public Optional<String> getCountry() {
		return Optional.ofNullable(country);
	}

	public Optional<String> getZipCode() {
		return Optional.ofNullable(zipCode);
	}

}
