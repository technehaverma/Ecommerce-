package com.app.ecomm.mapper;

import java.util.Optional;

import com.app.ecomm.dto.AddressResponse;
import com.app.ecomm.entity.Address;

public class AddressMapper {

	public void convertAddressToAddressResponse(Optional<Address> adderess, AddressResponse response) {
		
		if(adderess.isPresent()) {
			var add = adderess.get();
			response.setId(add.getId());
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
