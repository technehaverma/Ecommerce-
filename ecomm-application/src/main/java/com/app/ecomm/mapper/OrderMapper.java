package com.app.ecomm.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.app.ecomm.dto.CartItemResponse;
import com.app.ecomm.dto.OrderItemsDTO;
import com.app.ecomm.dto.OrderResponse;
import com.app.ecomm.entity.Order;
import com.app.ecomm.entity.OrderItems;

@Component
public class OrderMapper {

	public void convertCartItemsToOrderItems(List<CartItemResponse> cartItems, List<OrderItems> items, Order order) {
		cartItems.stream().map(it->{
			OrderItems orderItem = new OrderItems();
			orderItem.setId(it.getId());
			orderItem.setPrice(it.getPrice());
			orderItem.setProduct(it.getProduct());
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
			dto.setProduct(item.getProduct().getId());
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
