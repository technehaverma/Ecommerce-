package com.app.ecomm.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.app.ecomm.enums.OrderStatus;

import lombok.Data;

@Data
public class OrderResponse {

	private Long id;

	private BigDecimal total;

	private OrderStatus orderStatus = OrderStatus.PENDING;

	private List<OrderItemsDTO> orderItems = new ArrayList<>();
	private LocalDateTime createdAt;

}
