package com.app.ecomm.entity;

import java.time.LocalDateTime;
import java.util.Optional;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "address_line1")
	private String addressLine1;
	@Column(name = "address_line2")
	private String addressLine2;
	private String landmark;
	private String city;
	private String state;
	private String country;
	@Column(name = "zip_code")
	private String zipCode;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	/*
	 * @CreatedBy
	 * 
	 * @Column(name = "created_by") private String createdBy;
	 */
	@UpdateTimestamp
	@Column(name = "modified_at")
	private LocalDateTime modifiedAt;

	/*
	 * @LastModifiedBy
	 * 
	 * @Column(name = "modified_by") private String modifiedBy;
	 */
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
