package com.phegondev.AlekEcommerce.service.impl;

import com.phegondev.AlekEcommerce.dto.AddressDto;
import com.phegondev.AlekEcommerce.dto.Response;
import com.phegondev.AlekEcommerce.entity.Address;
import com.phegondev.AlekEcommerce.entity.User;
import com.phegondev.AlekEcommerce.repository.AddressRepo;
import com.phegondev.AlekEcommerce.service.interf.AddressService;
import com.phegondev.AlekEcommerce.service.interf.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepo addressRepo;
    private final UserService userService;

    @Override
    public Response saveAndUpdateAddress(AddressDto addressDto) {
        User user = userService.getLoginUser();
        Address address = user.getAddress();

        if (address == null) {
            address = new Address();
            address.setUser(user);
        }
        if (addressDto.getStreet() != null) address.setStreet(addressDto.getStreet());
        if (addressDto.getCity() != null) address.setCity(addressDto.getCity());
        if (addressDto.getState() != null) address.setState(addressDto.getState());
        if (addressDto.getZipCode() != null) address.setZipCode(addressDto.getZipCode());
        if (addressDto.getCountry() != null) address.setCountry(addressDto.getCountry());
        addressRepo.save(address);

        String message = (user.getAddress() == null) ? "Address saved successfully" : "Address updated successfully";
        return Response.builder()
                .status(200)
                .message(message)
                .build();


    }
}
