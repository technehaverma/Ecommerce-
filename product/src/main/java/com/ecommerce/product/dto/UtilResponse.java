package com.ecommerce.product.dto;

import org.springframework.http.HttpStatusCode;

import lombok.Data;

@Data
public class UtilResponse<T> {

	private T response;
	private HttpStatusCode code;
}
