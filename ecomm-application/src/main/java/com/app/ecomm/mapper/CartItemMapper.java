package com.app.ecomm.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.app.ecomm.dto.CartItemResponse;
import com.app.ecomm.entity.CartItem;

@Component
public class CartItemMapper {

	public void convertCartItemsToCartItemResponses(List<CartItem> cartItemList,
			List<CartItemResponse> cartItemResponses) {

		cartItemList.stream().map((c)->{
			CartItemResponse cartItemResponse = new CartItemResponse();
			cartItemResponse.setPrice(c.getPrice());
			cartItemResponse.setProduct(c.getProduct());
			cartItemResponse.setQuantity(c.getQuantity());
			cartItemResponse.setUser(c.getUser());
			cartItemResponses.add(cartItemResponse);
			return cartItemResponse;
		}).toList();
	}

}
