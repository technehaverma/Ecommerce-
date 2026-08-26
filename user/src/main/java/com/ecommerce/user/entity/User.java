package com.ecommerce.user.entity;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private UserRole role = UserRole.USER;

	private Address address;
    @CreatedDate
	private LocalDateTime createdAt;
	/*
	 * @CreatedBy
	 * 
	 * @Column(name = "created_by") private String createdBy;
	 */
	@LastModifiedDate
	private LocalDateTime modifiedAt;
	
	//private String modifiedBy;

	public String getId() {
		return id;
	}

	public Optional<String> getFirstName() {
		return Optional.ofNullable(firstName);
	}

	public Optional<String> getLastName() {
		return Optional.ofNullable(lastName);
	}

	public Optional<String> getEmail() {
		return Optional.ofNullable(email);
	}

	public Optional<String> getPhoneNumber() {
		return Optional.ofNullable(phoneNumber);
	}
	
	public Optional<Address> getAdderess() {
		return Optional.ofNullable(address);
	}


}
