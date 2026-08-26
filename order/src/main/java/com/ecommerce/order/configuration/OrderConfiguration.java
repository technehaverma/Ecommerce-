package com.ecommerce.order.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.ecommerce.order.clients.ProductServiceRestClient;
import com.ecommerce.order.clients.UserServiceRestClient;

@Configuration
public class OrderConfiguration {
	
	@Bean
	
	public RestClient.Builder restClient() {
		return RestClient.builder();
	}
	
	@Bean
	@Lazy
	public ProductServiceRestClient restproductClientInterface(RestClient.Builder builder) {
		RestClient restClient = builder.baseUrl("lb://PRODUCT-SERVICE").build();
		RestClientAdapter clientAdapter = RestClientAdapter.create(restClient);
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(clientAdapter).build();
		ProductServiceRestClient productServiceRestClient = factory.createClient(ProductServiceRestClient.class);
		return productServiceRestClient;
		
	}
	@Bean
	@Lazy
	public UserServiceRestClient restUserClientInterface(RestClient.Builder builder) {
		RestClient restClient = builder.baseUrl("lb://USER").build();
		RestClientAdapter clientAdapter = RestClientAdapter.create(restClient);
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(clientAdapter).build();
		UserServiceRestClient userServiceRestClient = factory.createClient(UserServiceRestClient.class);
		return userServiceRestClient;
		
	}

}
