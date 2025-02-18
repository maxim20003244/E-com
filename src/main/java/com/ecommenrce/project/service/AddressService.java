package com.ecommenrce.project.service;

import com.ecommenrce.project.dto.AddressDTO;
import com.ecommenrce.project.model.Users;

import java.util.List;


public interface AddressService {
    AddressDTO createAddress(Users user, AddressDTO addressDTO);

    List<AddressDTO> getAllAddresses();

    AddressDTO getAddressById(Long addressId);

    List<AddressDTO> getAddressByUser(Users users);

    AddressDTO updateAddressById(Long addressId, AddressDTO addressDTO);

    AddressDTO deleteAddressById(Long addressId);

}


