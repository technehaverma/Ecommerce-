package com.ecommerce.order.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.order.enums.OrderStatus;

import lombok.Data;

@Data
public class OrderRequest {

	private Long id;

	private String userId;
	private BigDecimal total;

	private OrderStatus orderStatus = OrderStatus.PENDING;

	private List<OrderItemsDTO> orderItems = new ArrayList<>();

}
