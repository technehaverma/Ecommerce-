package com.ecommerce.order.clients;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.ecommerce.order.dto.ProductResponse;
import com.ecommerce.order.dto.UtilResponse;

@HttpExchange
public interface ProductServiceRestClient {

	@GetExchange(accept = "application/json",value = "/products/{id}")
	public ResponseEntity<UtilResponse<ProductResponse>> getProductById(@PathVariable("id") String id);
}
