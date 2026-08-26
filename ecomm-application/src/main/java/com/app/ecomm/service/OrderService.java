package com.app.ecomm.service;

import java.util.Optional;

import com.app.ecomm.dto.OrderResponse;

public interface OrderService {

	Optional<OrderResponse> createOrder(String userId);

}
