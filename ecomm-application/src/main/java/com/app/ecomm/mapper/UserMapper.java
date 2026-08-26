package com.app.ecomm.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.app.ecomm.dto.AddressResponse;
import com.app.ecomm.dto.UserResponse;
import com.app.ecomm.entity.User;

@Component
public class UserMapper {

	AddressMapper mapper = new AddressMapper();

	public void convertUserListToUserResponse(List<User> user, List<UserResponse> userResponseList) {

		user.stream().map(u -> {
			UserResponse us = new UserResponse();
			AddressResponse response = new AddressResponse();
			us.setId(String.valueOf(u.getId()));
			us.setFirstName(u.getFirstName().orElse(""));
			us.setLastName(u.getLastName().orElse(""));
			us.setPhoneNumber(u.getPhoneNumber().orElse(""));
			us.setEmail(u.getEmail().orElse(""));
			mapper.convertAddressToAddressResponse(u.getAdderess(), response);
			us.setAddress(response);
			userResponseList.add(us);
			return us;
		}).toList();
	}

	public void convertUserToUserResponse(User user, UserResponse userResponse) {
		AddressResponse response = new AddressResponse();
		userResponse.setId(String.valueOf(user.getId()));
		userResponse.setFirstName(user.getFirstName().orElse(""));
		userResponse.setLastName(user.getLastName().orElse(""));
		userResponse.setPhoneNumber(user.getPhoneNumber().orElse(""));
		userResponse.setEmail(user.getEmail().orElse(""));
		mapper.convertAddressToAddressResponse(user.getAdderess(), response);
		userResponse.setAddress(response);

	}

}
