package com.ecommerce.product.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private Integer stockQuantity;
	private String category;
	private String imageUrl;
	private Boolean active;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Optional<String> getName() {
		return Optional.ofNullable(name);
	}

	public Optional<String> getDescription() {
		return Optional.ofNullable(description);
	}

	public Optional<BigDecimal> getPrice() {
		return Optional.ofNullable(price);
	}

	public Optional<Integer> getStockQuantity() {
		return Optional.ofNullable(stockQuantity);
	}

	public Optional<String> getCategory() {
		return Optional.ofNullable(category);
	}

	public Optional<String> getImageUrl() {
		return Optional.ofNullable(imageUrl);
	}

}
