package com.app.ecomm.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecomm.dto.CartItemRequest;
import com.app.ecomm.dto.CartItemResponse;
import com.app.ecomm.dto.UtilResponse;
import com.app.ecomm.service.CartItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

	private final CartItemService cartItemService;

	@PostMapping
	public ResponseEntity<String> addToCart(@RequestHeader("X-USER-ID") String userId,
			@RequestBody CartItemRequest cartItemRequest) {

		return cartItemService.addToCart(userId, cartItemRequest) ? ResponseEntity.status(HttpStatus.CREATED).build()
				: ResponseEntity.badRequest().body("Product Out of Stock or User Not Found");
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<String> removeFromCart(@RequestHeader("X-USER-ID") String userId,
			@PathVariable("productId") String productId) {
		

		return cartItemService.removeFromCart(userId, productId) ? ResponseEntity.status(HttpStatus.FOUND).build()
				: ResponseEntity.badRequest().body("Product Not Found");
	}
	
	@GetMapping
	public ResponseEntity<?> getCartProducts(@RequestHeader("X-USER-ID") String userId){
		
		List<CartItemResponse> cartItemResponses = cartItemService.getAllCartProducts(userId);
		UtilResponse<List<CartItemResponse>> response = new UtilResponse<>();
		if(cartItemResponses.size()>0) {
	
		response.setResponse(cartItemResponses);
		response.setCode(HttpStatusCode.valueOf(200));
		return new ResponseEntity<UtilResponse<List<CartItemResponse>>>(response, HttpStatusCode.valueOf(200));
		}else {
			return new ResponseEntity<String>("No Element Found", HttpStatusCode.valueOf(200));
		}
	}
}
