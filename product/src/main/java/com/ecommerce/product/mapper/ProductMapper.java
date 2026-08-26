package com.ecommerce.product.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ecommerce.product.dto.ProductRequest;
import com.ecommerce.product.dto.ProductResponse;
import com.ecommerce.product.entity.Product;

@Component
public class ProductMapper {

	public void convertProductToProductResponse(Product product, ProductResponse productResponse) {
		if (product != null) {
			productResponse.setId(String.valueOf(product.getId()));
			productResponse.setName(product.getName().orElse(""));
			productResponse.setDescription(product.getDescription().orElse(""));
			productResponse.setPrice(String.valueOf(product.getPrice().orElse(null)));
			productResponse.setCategory(product.getCategory().orElse(""));
			productResponse.setStockQuantity(String.valueOf(product.getStockQuantity().orElse(0)));
			productResponse.setImageUrl(product.getImageUrl().orElse(""));
			productResponse.setActive(product.getActive());
		}
	}

	public void convertProductListToProductResponseList(List<Product> products,
			List<ProductResponse> productResponses) {

		products.stream().map((p) -> {
			ProductResponse productResponse = new ProductResponse();
			productResponse.setId(String.valueOf(p.getId()));
			productResponse.setName(p.getName().orElse(""));
			productResponse.setDescription(p.getDescription().orElse(""));
			productResponse.setPrice(String.valueOf(p.getPrice().orElse(null)));
			productResponse.setCategory(p.getCategory().orElse(""));
			productResponse.setStockQuantity(String.valueOf(p.getStockQuantity().orElse(0)));
			productResponse.setImageUrl(p.getImageUrl().orElse(""));
			productResponse.setActive(p.getActive());
			productResponses.add(productResponse);
			return productResponse;
		}).toList();

	}

	public void convertProductRequestToProduct(ProductRequest productRequest, Product product) {

		if (productRequest != null) {
			if(productRequest.getId()!=null) {
				product.setId(Long.parseLong(productRequest.getId()));
			}
			product.setName(productRequest.getName());
			product.setDescription(productRequest.getDescription());
			product.setPrice(BigDecimal.valueOf(Long.parseLong(productRequest.getPrice())));
			product.setCategory(productRequest.getCategory());
			product.setStockQuantity(Integer.parseInt(productRequest.getStockQuantity()));
			product.setActive(productRequest.getActive());
			product.setImageUrl(productRequest.getImageUrl());
		}
	}
}
