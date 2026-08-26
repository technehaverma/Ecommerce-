package com.ecommerce.order.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemsDTO {

	private Long id;

	private String productId;
	private BigDecimal price;
	private Integer quantity;
	private BigDecimal subTotal;

}
