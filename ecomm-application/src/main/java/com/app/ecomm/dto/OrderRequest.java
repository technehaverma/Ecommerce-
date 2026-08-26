package com.app.ecomm.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.app.ecomm.enums.OrderStatus;

import lombok.Data;

@Data
public class OrderRequest {

	private Long id;

	private Long userId;
	private BigDecimal total;

	private OrderStatus orderStatus = OrderStatus.PENDING;

	private List<OrderItemsDTO> orderItems = new ArrayList<>();

}
