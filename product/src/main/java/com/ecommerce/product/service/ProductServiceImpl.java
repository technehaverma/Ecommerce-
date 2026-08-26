package com.ecommerce.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.entity.Product;
import com.ecommerce.product.mapper.ProductMapper;
import com.ecommerce.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ProductMapper mapper;

	@Override
	public List<ProductResponse> getAllProducts() {
		List<ProductResponse> productResponses = new ArrayList<>();
		List<Product> products = productRepository.findAll();
		mapper.convertProductListToProductResponseList(products, productResponses);

		return productResponses;
	}

	@Override
	public ProductResponse getById(String id) {
		ProductResponse productResponse = new ProductResponse();
		Product product = productRepository.getReferenceById(Long.parseLong(id));
		mapper.convertProductToProductResponse(product, productResponse);
		return productResponse;
	}

	@Override
	public String save(ProductRequest productRequest) {
		Product product = new Product();
		mapper.convertProductRequestToProduct(productRequest, product);
		try {
			productRepository.save(product);
		} catch (Exception e) {
			return "Failure";
		}
		return "Success";
	}

	@Override
	public String save(ProductRequest productRequest, String id) {
		
		Product product = new Product();
		mapper.convertProductRequestToProduct(productRequest, product);
		try {
			productRepository.save(product);
		}catch (Exception e) {
			return "Failure";
		}
		return "Success";
	}

	@Override
	public Boolean deleteById(String id) {
		boolean flag = productRepository.existsByIdAndActive(Long.parseLong(id),true);
		if(flag) {
			productRepository.updateActiveStatus(Long.parseLong(id));
		}else return false;
		
		return true;
	}

	@Override
	public List<ProductResponse> getProductsByKeyword(String keyword) {

		List<ProductResponse> productResponses = new ArrayList<>();
		List<Product> products = productRepository.findByKeyword(keyword);
		mapper.convertProductListToProductResponseList(products, productResponses);
		
		return productResponses;
	}

}
