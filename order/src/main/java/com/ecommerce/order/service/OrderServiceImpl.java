package com.ecommerce.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.order.dto.CartItemResponse;
import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderItems;
import com.ecommerce.order.enums.OrderStatus;
import com.ecommerce.order.mapper.OrderMapper;
import com.ecommerce.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final OrderMapper orderMapper;
	private final CartItemService cartItemService;
	private final OrderRepository orderRepository;

	@Override
	public Optional<OrderResponse> createOrder(String userId) {
		OrderResponse orderResponse = new OrderResponse();
		//check if user is valid
		/*
		 * Optional<User> userExist = userRepository.findById(Long.valueOf(userId));
		 * if(!userExist.isPresent()) { return Optional.ofNullable(orderResponse); }
		 */
		// check if the product is their in cart
		List<CartItemResponse> cartItems = cartItemService.getAllCartProducts(userId);
		if(cartItems.isEmpty()) {
			return Optional.ofNullable(orderResponse);
		}
		//validate total price
		//BigDecimal price = cartItems.stream().map(CartItemResponse::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
		//create order
		List<OrderItems> items = new ArrayList<>();
		Order order = new Order();
		order.setOrderStatus(OrderStatus.CONFIRMED);
		order.setTotal(new BigDecimal(1000.00));
		order.setUserId(userId);
		orderMapper.convertCartItemsToOrderItems(cartItems, items, order);
		order.setOrderItems(items);
		Order savedOrder = orderRepository.save(order);
		orderMapper.convertOrderToOrderResponse(savedOrder, orderResponse);
		//clear cart
		cartItemService.clearCart(userId);
		
		return Optional.of(orderResponse);
	}

}
