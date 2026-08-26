package com.ecommerce.product.dto;

import lombok.Data;

@Data
public class ProductRequest {

	private String id;
	private String name;
	private String description;
	private String price;
	private String stockQuantity;
	private String category;
	private String imageUrl;
	private Boolean active;
}
