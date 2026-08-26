package com.app.ecomm.controller;

import java.util.Optional;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.ecomm.dto.OrderRequest;
import com.app.ecomm.dto.OrderResponse;
import com.app.ecomm.dto.UtilResponse;
import com.app.ecomm.service.OrderService;

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
