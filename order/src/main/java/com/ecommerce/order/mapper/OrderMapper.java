package com.ecommerce.order.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ecommerce.order.dto.CartItemResponse;
import com.ecommerce.order.dto.OrderItemsDTO;
import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderItems;

@Component
public class OrderMapper {

	public void convertCartItemsToOrderItems(List<CartItemResponse> cartItems, List<OrderItems> items, Order order) {
		cartItems.stream().map(it->{
			OrderItems orderItem = new OrderItems();
			orderItem.setId(it.getId());
			orderItem.setPrice(it.getPrice());
			orderItem.setProductId(it.getProductId());
			orderItem.setQuantity(it.getQuantity());
			orderItem.setOrder(order);
			items.add(orderItem);
			return orderItem;
		}).toList();
	}

	public void convertOrderToOrderResponse(Order savedOrder, OrderResponse orderResponse) {
		List<OrderItems> orderItem = savedOrder.getOrderItems();
		List<OrderItemsDTO> itemsDTOs = new ArrayList<>();
		orderItem.stream().map(item->{
			OrderItemsDTO dto = new OrderItemsDTO();
			dto.setPrice(item.getPrice());
			dto.setProductId(item.getProductId());
			dto.setQuantity(item.getQuantity());
			dto.setSubTotal(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
			dto.setId(item.getId());
			itemsDTOs.add(dto);
			return dto;
		}
		).toList();
		orderResponse.setId(savedOrder.getId());
		orderResponse.setOrderItems(itemsDTOs);
		orderResponse.setOrderStatus(savedOrder.getOrderStatus());
		orderResponse.setTotal(savedOrder.getTotal());
		orderResponse.setCreatedAt(savedOrder.getCreatedAt());
	}

}
