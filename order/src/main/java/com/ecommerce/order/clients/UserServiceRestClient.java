package com.ecommerce.order.clients;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.ecommerce.order.dto.UserResponse;
import com.ecommerce.order.dto.UtilResponse;

@HttpExchange
public interface UserServiceRestClient {

	@GetExchange(accept = "application/json", value = "/user/{id}")
	public ResponseEntity<UtilResponse<UserResponse>> getUserById(@PathVariable("id") String id);
}
