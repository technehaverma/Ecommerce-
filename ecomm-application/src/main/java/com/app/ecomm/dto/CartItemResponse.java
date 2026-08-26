package com.app.ecomm.dto;

import java.math.BigDecimal;

import com.app.ecomm.entity.Product;
import com.app.ecomm.entity.User;

import lombok.Data;

@Data
public class CartItemResponse {

	private Long id;

	private User user;
	private Product product;
	private Integer quantity;
	private BigDecimal price;

}
