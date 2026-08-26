package com.ecommerce.user.dto;

import org.springframework.beans.factory.annotation.Value;

import lombok.Data;

@Data
public class UserInfo {
	
	@Value("${user.name}")
	private String projectName;

}
