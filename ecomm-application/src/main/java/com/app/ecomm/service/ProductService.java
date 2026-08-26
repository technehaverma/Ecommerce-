package com.app.ecomm.service;

import java.util.List;

import com.app.ecomm.dto.ProductRequest;
import com.app.ecomm.dto.ProductResponse;

public interface ProductService {

	List<ProductResponse> getAllProducts();

	ProductResponse getById(String id);

	String save(ProductRequest productRequest);

	String save(ProductRequest productRequest, String id);

	Boolean deleteById(String id);

	List<ProductResponse> getProductsByKeyword(String keyword);

}
