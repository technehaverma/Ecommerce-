package com.ecommerce.order.service;

import java.util.List;

import com.ecommerce.order.dto.CartItemRequest;
import com.ecommerce.order.dto.CartItemResponse;

public interface CartItemService {

	boolean addToCart(String userId, CartItemRequest cartItemRequest);

	boolean removeFromCart(String userId, String productId);

	List<CartItemResponse> getAllCartProducts(String userId);

	void clearCart(String userId);

}
