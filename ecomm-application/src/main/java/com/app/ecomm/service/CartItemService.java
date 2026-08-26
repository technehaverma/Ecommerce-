package com.app.ecomm.service;

import java.util.List;

import com.app.ecomm.dto.CartItemRequest;
import com.app.ecomm.dto.CartItemResponse;

public interface CartItemService {

	boolean addToCart(String userId, CartItemRequest cartItemRequest);

	boolean removeFromCart(String userId, String productId);

	List<CartItemResponse> getAllCartProducts(String userId);

	void clearCart(String userId);

}
