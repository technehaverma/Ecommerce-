package com.ecommerce.order.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data

public class UtilResponse<T> {

	private T response;
	private HttpStatusCode code;
	@JsonCreator
    public static <T> UtilResponse<T> create(
            @JsonProperty("response") T response,
            @JsonProperty("code") Object codeObj) {
        
        UtilResponse<T> utilResponse = new UtilResponse<>();
        utilResponse.setResponse(response);
        
        if (codeObj instanceof Number number) {
            utilResponse.setCode(HttpStatusCode.valueOf(number.intValue()));
        } else if (codeObj instanceof String str) {
            // Handles named statuses if your API passes strings like "OK" or numeric strings "200"
            try {
                utilResponse.setCode(HttpStatusCode.valueOf(Integer.parseInt(str)));
            } catch (NumberFormatException e) {
                // If it is an alpha-string status (e.g., "OK"), fallback to standard HttpStatus enum resolver
                //utilResponse.setCode(HttpStatus.valueOf(str.toUpperCase()));
            }
        }
        return utilResponse;
	}
}
