package com.ecommerce.order.controller;

import java.util.Optional;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.dto.OrderRequest;
import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.order.dto.UtilResponse;
import com.ecommerce.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;
	
	@PostMapping
	public ResponseEntity<?> createOrder(@RequestHeader("X-USER-ID") String userId, @RequestBody OrderRequest order){
		UtilResponse<OrderResponse> response = new UtilResponse<>();
		
		Optional<OrderResponse> resp = orderService.createOrder(userId);
		if(resp.isPresent()) {
		response.setCode(HttpStatusCode.valueOf(200));
		response.setResponse(resp.get());
		return new ResponseEntity<UtilResponse<OrderResponse>>(response, HttpStatusCode.valueOf(200));
		}else {
			UtilResponse<String> response2 = new UtilResponse<>();
			response2.setResponse("Failure");
			response2.setCode(HttpStatusCode.valueOf(400));
			return new ResponseEntity<UtilResponse<String>>(response2, HttpStatusCode.valueOf(400));
		}
	}

}
