package com.ecommerce.product.service;

import java.util.List;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;

public interface ProductService {

	List<ProductResponse> getAllProducts();

	ProductResponse getById(String id);

	String save(ProductRequest productRequest);

	String save(ProductRequest productRequest, String id);

	Boolean deleteById(String id);

	List<ProductResponse> getProductsByKeyword(String keyword);

}
