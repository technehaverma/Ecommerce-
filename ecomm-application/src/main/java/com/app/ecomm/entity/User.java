package com.app.ecomm.entity;

import java.time.LocalDateTime;
import java.util.Optional;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedBy;

import com.app.ecomm.enums.UserRole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private String email;
	@Column(name = "phone_number")
	private String phoneNumber;
	private UserRole role = UserRole.USER;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private Address address;

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
	@LastModifiedBy
	@Column(name = "modified_by")
	private String modifiedBy;

	public Long getId() {
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
