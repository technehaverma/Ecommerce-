package com.app.ecomm.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderItemsDTO {

	private Long id;

	private Long product;
	private BigDecimal price;
	private Integer quantity;
	private BigDecimal subTotal;

}
