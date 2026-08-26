package com.ecommerce.user.mapper;

import java.util.Optional;

import com.ecommerce.user.dto.AddressResponse;
import com.ecommerce.user.entity.Address;

public class AddressMapper {

	public void convertAddressToAddressResponse(Optional<Address> adderess, AddressResponse response) {

		if (adderess.isPresent()) {
			var add = adderess.get();
			if (add.getId() != null) {
				response.setId(add.getId().toString());
			}
			response.setAddressLine1(add.getAddressLine1().orElse(""));
			response.setAddressLine2(add.getAddressLine2().orElse(""));
			response.setCity(add.getCity().orElse(""));
			response.setState(add.getState().orElse(""));
			response.setCountry(add.getCountry().orElse(""));
			response.setLandmark(add.getLandmark().orElse(""));
			response.setZipCode(add.getZipCode().orElse(""));
		}
	}

}
