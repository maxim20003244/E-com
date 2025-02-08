package com.ecommenrce.project.serviceImpl;

import com.ecommenrce.project.dto.AddressDTO;
import com.ecommenrce.project.model.Address;
import com.ecommenrce.project.model.Users;
import com.ecommenrce.project.repository.AddressRepository;
import com.ecommenrce.project.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public AddressDTO createAddress(Users user, AddressDTO addressDTO) {
        Address  address = modelMapper.map(addressDTO, Address.class);
        List<Address> addressList = user.getAddresses();
        addressList.add(address);
        user.setAddresses(addressList);
        address.setUser(user);
        Address savedAddress = addressRepository.save(address);
        AddressDTO savedAddressDTO = modelMapper.map(savedAddress,AddressDTO.class);

        return savedAddressDTO;
    }
}
