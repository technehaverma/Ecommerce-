package com.app.ecomm.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.ecomm.dto.CartItemRequest;
import com.app.ecomm.dto.CartItemResponse;
import com.app.ecomm.entity.CartItem;
import com.app.ecomm.entity.Product;
import com.app.ecomm.entity.User;
import com.app.ecomm.mapper.CartItemMapper;
import com.app.ecomm.repository.CartItemRepository;
import com.app.ecomm.repository.ProductRepository;
import com.app.ecomm.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

	private final CartItemRepository cartItemRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final CartItemMapper cartItemMapper;

	@Override
	public boolean addToCart(String userId, CartItemRequest cartItemRequest) throws NumberFormatException {
		CartItem cartItem = new CartItem();
		Long productId = cartItemRequest.getProductId();
		Optional<Product> product = productRepository.findById(productId);
		if (product.isEmpty()) {
			return false;
		}
		Product productRes = product.get();
		if (productRes.getStockQuantity().get() < cartItemRequest.getQuantity()) {
			return false;
		}
		Optional<User> userOpt = userRepository.findById(Long.parseLong(userId));
		if (userOpt.isEmpty()) {
			return false;
		}
		User user = userOpt.get();
		CartItem existingCartItem = new CartItem();
		try {
			existingCartItem = cartItemRepository.findByUserAndProduct(user, productRes);

		} catch (NumberFormatException e) {
			return false;
		}
		if (existingCartItem != null) {
			existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemRequest.getQuantity());
			if (productRes.getStockQuantity().get() < existingCartItem.getQuantity()) {
				return false;
			}
			existingCartItem
					.setPrice(productRes.getPrice().get().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
			cartItemRepository.save(existingCartItem);
			return true;
		} else {
			cartItem.setPrice(productRes.getPrice().get().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())));
			cartItem.setQuantity(cartItemRequest.getQuantity());
			cartItem.setProduct(productRes);
			cartItem.setUser(user);
			cartItemRepository.save(cartItem);
			return true;
		}

	}

	@Transactional
	@Override
	public boolean removeFromCart(String userId, String productId) {
		Optional<Product> product = productRepository.findById(Long.valueOf(productId));
		if (product.isEmpty()) {
			return false;
		}
		Product productRes = product.get();

		Optional<User> userOpt = userRepository.findById(Long.parseLong(userId));
		if (userOpt.isEmpty()) {
			return false;
		}
		User user = userOpt.get();

		cartItemRepository.deleteByUserAndProduct(user, productRes);
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
		List<CartItem> cartItemList = userRepository.findById(Long.valueOf(userId))
				.map(u -> cartItemRepository.findByUser(u)).orElseGet(List::of);
		if (cartItemList != null && cartItemList.size() > 0) {

			cartItemMapper.convertCartItemsToCartItemResponses(cartItemList, cartItemResponses);
		}

		return cartItemResponses;
	}

	@Override
	public void clearCart(String userId) {
		userRepository.findById(Long.valueOf(userId)).ifPresent(u -> cartItemRepository.deleteByUser(u));
	}

}
