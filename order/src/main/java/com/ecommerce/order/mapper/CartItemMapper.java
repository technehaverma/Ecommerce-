package com.ecommerce.order.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ecommerce.order.dto.CartItemResponse;
import com.ecommerce.order.entity.CartItem;

@Component
public class CartItemMapper {

	public void convertCartItemsToCartItemResponses(List<CartItem> cartItemList,
			List<CartItemResponse> cartItemResponses) {

		cartItemList.stream().map((c)->{
			CartItemResponse cartItemResponse = new CartItemResponse();
			cartItemResponse.setPrice(c.getPrice());
			cartItemResponse.setProductId(c.getProductId());
			cartItemResponse.setQuantity(c.getQuantity());
			cartItemResponse.setUserId(c.getUserId());
			cartItemResponses.add(cartItemResponse);
			return cartItemResponse;
		}).toList();
	}

}
