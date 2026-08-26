package com.ecommerce.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.order.clients.ProductServiceRestClient;
import com.ecommerce.order.clients.UserServiceRestClient;
import com.ecommerce.order.dto.CartItemRequest;
import com.ecommerce.order.dto.CartItemResponse;
import com.ecommerce.order.dto.ProductResponse;
import com.ecommerce.order.dto.UserResponse;
import com.ecommerce.order.dto.UtilResponse;
import com.ecommerce.order.entity.CartItem;
import com.ecommerce.order.mapper.CartItemMapper;
import com.ecommerce.order.repository.CartItemRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

	private final CartItemRepository cartItemRepository;
	private final CartItemMapper cartItemMapper;
	private final ProductServiceRestClient productServiceRestClient;
	private final UserServiceRestClient userServiceRestClient;

	@Override
	public boolean addToCart(String userId, CartItemRequest cartItemRequest) throws NumberFormatException {
		CartItem cartItem = new CartItem();
		String productId = cartItemRequest.getProductId();

		ResponseEntity<UtilResponse<ProductResponse>> productResponse = productServiceRestClient.getProductById(productId);
		ProductResponse product = productResponse.getBody().getResponse();
		if (product == null) {
			return false;
		}
		if (Integer.parseInt(product.getStockQuantity()) < cartItemRequest.getQuantity()) {
			return false;
		}
		ResponseEntity<UtilResponse<UserResponse>> userOptRes = userServiceRestClient.getUserById(userId);
		UtilResponse<UserResponse> userOpt = userOptRes.getBody();
		if (userOpt == null || userOpt.getResponse() == null) {
			return false;
		}

		CartItem existingCartItem = new CartItem();
		try {
			existingCartItem = cartItemRepository.findByUserIdAndProductId(userOpt.getResponse().getId(), productId);

		} catch (NumberFormatException e) {
			return false;
		}
		if (existingCartItem != null) {
			existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemRequest.getQuantity());

			if (Long.parseLong(product.getStockQuantity()) < existingCartItem.getQuantity()) {
				return false;
			}
			existingCartItem.setPrice(
					new BigDecimal(product.getPrice()).multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));

			cartItemRepository.save(existingCartItem);
			return true;
		} else {
			cartItem.setPrice(
					new BigDecimal(product.getPrice()).multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())));
			cartItem.setQuantity(cartItemRequest.getQuantity());
			cartItem.setProductId(productId);
			cartItem.setUserId(userId);
			cartItemRepository.save(cartItem);
			return true;
		}

	}

	@Transactional
	@Override
	public boolean removeFromCart(String userId, String productId) {
		/*
		 * Optional<Product> product =
		 * productRepository.findById(Long.valueOf(productId)); if (product.isEmpty()) {
		 * return false; } Product productRes = product.get();
		 * 
		 * Optional<User> userOpt = userRepository.findById(Long.parseLong(userId)); if
		 * (userOpt.isEmpty()) { return false; } User user = userOpt.get();
		 */
		cartItemRepository.deleteByUserIdAndProductId(userId, productId);
		return true;

	}

	@Override
	public List<CartItemResponse> getAllCartProducts(String userId) {
		/*
		 * Optional<User> userOpt = userRepository.findById(Long.parseLong(userId)); if
		 * (userOpt.isPresent()) { List<CartItem> cartItemList =
		 * cartItemRepository.findByUser(userOpt.get());
		 */
		List<CartItemResponse> cartItemResponses = new ArrayList<>();
		List<CartItem> cartItemList = cartItemRepository.findByUserId(userId).orElseGet(List::of);
		if (cartItemList != null && cartItemList.size() > 0) {

			cartItemMapper.convertCartItemsToCartItemResponses(cartItemList, cartItemResponses);
		}

		return cartItemResponses;
	}

	@Override
	public void clearCart(String userId) {
		cartItemRepository.deleteByUserId(userId);
	}

}
