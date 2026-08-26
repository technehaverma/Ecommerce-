package com.ecommerce.order.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ecommerce.order.enums.OrderStatus;

import lombok.Data;

@Data
public class OrderResponse {

	private Long id;

	private BigDecimal total;

	private OrderStatus orderStatus = OrderStatus.PENDING;

	private List<OrderItemsDTO> orderItems = new ArrayList<>();
	private LocalDateTime createdAt;

}
