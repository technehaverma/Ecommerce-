package com.ecommerce.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.dto.UtilResponse;
import com.ecommerce.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;

	@GetMapping
	public ResponseEntity<?> getAllProducts() {
		List<ProductResponse> productList = new ArrayList<>();
		try {
			productList = productService.getAllProducts();
		} catch (Exception e) {
			return new ResponseEntity<String>("Failure", HttpStatusCode.valueOf(400));
		}
		UtilResponse<List<ProductResponse>> response = new UtilResponse<>();
		response.setCode(HttpStatusCode.valueOf(200));
		response.setResponse(productList);
		return new ResponseEntity<UtilResponse<List<ProductResponse>>>(response, HttpStatusCode.valueOf(200));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable("id") String id) {
		UtilResponse<ProductResponse> response = new UtilResponse<>();
		ProductResponse prodResponse = new ProductResponse();
		try {
			prodResponse = productService.getById(id);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failure", HttpStatusCode.valueOf(400));
		}
		response.setCode(HttpStatusCode.valueOf(200));
		response.setResponse(prodResponse);
		return new ResponseEntity<UtilResponse<ProductResponse>>(response, HttpStatusCode.valueOf(200));

	}

	@PostMapping
	public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest) {
		UtilResponse<String> response = new UtilResponse<>();

		String resp = productService.save(productRequest);
		if (resp != null) {
			response.setCode(HttpStatusCode.valueOf(200));
			response.setResponse("Success");
			return new ResponseEntity<UtilResponse<String>>(response, HttpStatusCode.valueOf(200));
		} else {
			response.setCode(HttpStatusCode.valueOf(400));
			response.setResponse("Failure");
			return new ResponseEntity<UtilResponse<String>>(response, HttpStatusCode.valueOf(400));
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> createProduct(@PathVariable("id") String id, @RequestBody ProductRequest productRequest) {
		UtilResponse<String> response = new UtilResponse<>();

		String resp = productService.save(productRequest, id);
		if (resp != null) {
			response.setCode(HttpStatusCode.valueOf(200));
			response.setResponse("Success");
			return new ResponseEntity<UtilResponse<String>>(response, HttpStatusCode.valueOf(200));
		} else {
			response.setCode(HttpStatusCode.valueOf(400));
			response.setResponse("Failure");
			return new ResponseEntity<UtilResponse<String>>(response, HttpStatusCode.valueOf(400));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteByProductById(@PathVariable("id") String id) {
		UtilResponse<Boolean> response = new UtilResponse<>();
		boolean flag = productService.deleteById(id);
		if (!flag)
			return new ResponseEntity<String>("Failure", HttpStatusCode.valueOf(400));
		response.setCode(HttpStatusCode.valueOf(200));
		response.setResponse(flag);
		return new ResponseEntity<UtilResponse<Boolean>>(response, HttpStatusCode.valueOf(200));

	}
	
	@GetMapping("/searchproduct")
	public ResponseEntity<?> searchByProductById(@RequestParam("keyword") String keyword) {
		List<ProductResponse> productList = new ArrayList<>();
		try {
			productList = productService.getProductsByKeyword(keyword);
		} catch (Exception e) {
			return new ResponseEntity<String>("Failure", HttpStatusCode.valueOf(400));
		}
		UtilResponse<List<ProductResponse>> response = new UtilResponse<>();
		response.setCode(HttpStatusCode.valueOf(200));
		response.setResponse(productList);
		return new ResponseEntity<UtilResponse<List<ProductResponse>>>(response, HttpStatusCode.valueOf(200));

	}
}
