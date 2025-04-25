package com.phegondev.AlekEcommerce.service.interf;

import com.phegondev.AlekEcommerce.dto.AddressDto;
import com.phegondev.AlekEcommerce.dto.Response;

public interface AddressService {
    Response saveAndUpdateAddress(AddressDto addressDto);

}
