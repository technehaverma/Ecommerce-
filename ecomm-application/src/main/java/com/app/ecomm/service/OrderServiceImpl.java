package com.app.ecomm.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.ecomm.dto.CartItemResponse;
import com.app.ecomm.dto.OrderResponse;
import com.app.ecomm.entity.Order;
import com.app.ecomm.entity.OrderItems;
import com.app.ecomm.entity.User;
import com.app.ecomm.enums.OrderStatus;
import com.app.ecomm.mapper.OrderMapper;
import com.app.ecomm.repository.OrderRepository;
import com.app.ecomm.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	private final UserRepository userRepository;
	private final OrderMapper orderMapper;
	private final CartItemService cartItemService;
	private final OrderRepository orderRepository;

	@Override
	public Optional<OrderResponse> createOrder(String userId) {
		OrderResponse orderResponse = new OrderResponse();
		//check if user is valid
		Optional<User> userExist = userRepository.findById(Long.valueOf(userId));
		if(!userExist.isPresent()) {
			return Optional.ofNullable(orderResponse);
		}
		// check if the product is their in cart
		List<CartItemResponse> cartItems = cartItemService.getAllCartProducts(userId);
		if(cartItems.isEmpty()) {
			return Optional.ofNullable(orderResponse);
		}
		//validate total price
		BigDecimal price = cartItems.stream().map(CartItemResponse::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
		//create order
		List<OrderItems> items = new ArrayList<>();
		Order order = new Order();
		order.setOrderStatus(OrderStatus.CONFIRMED);
		order.setTotal(price);
		order.setUser(userExist.get());
		orderMapper.convertCartItemsToOrderItems(cartItems, items, order);
		order.setOrderItems(items);
		Order savedOrder = orderRepository.save(order);
		orderMapper.convertOrderToOrderResponse(savedOrder, orderResponse);
		//clear cart
		cartItemService.clearCart(userId);
		
		return Optional.of(orderResponse);
	}

}
