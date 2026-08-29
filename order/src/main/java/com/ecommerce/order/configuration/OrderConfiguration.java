package com.ecommerce.order.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.ecommerce.order.clients.ProductServiceRestClient;
import com.ecommerce.order.clients.UserServiceRestClient;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.propagation.Propagator;

@Configuration
public class OrderConfiguration {
	
	@Autowired(required = false)
	private ObservationRegistry observationRegistry;
	
	@Autowired(required = false)
	private Tracer tracer;
	
	@Autowired(required = false)
	private Propagator propagator;
	
	@Bean
	@LoadBalanced
	public RestClient.Builder restClient() {
		RestClient.Builder builder = RestClient.builder();
		
		if(observationRegistry!=null) {
			builder.requestInterceptor(createTracingInterceptor());
		}
		return builder;
	}
	
	private ClientHttpRequestInterceptor createTracingInterceptor() {
		
		return ((req,body,execution) -> {
			if(tracer!=null && propagator!=null && tracer.currentSpan()!=null) {
				propagator.inject(tracer.currentTraceContext().context(),
						req.getHeaders(), (carrier,key,value)-> carrier.add(key, value));
			}
			return execution.execute(req, body);	
		});
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
