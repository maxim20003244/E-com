package com.ecommenrce.project.serviceImpl;

import com.ecommenrce.project.dto.AddressDTO;
import com.ecommenrce.project.exception.ResourceNotFoundException;
import com.ecommenrce.project.model.Address;
import com.ecommenrce.project.model.Product;
import com.ecommenrce.project.model.Users;
import com.ecommenrce.project.repository.AddressRepository;
import com.ecommenrce.project.service.AddressService;
import com.ecommenrce.project.util.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    AuthUtil authUtil;
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

    @Override
    public List<AddressDTO> getAllAddresses() {
       List<Address> allAddresses =  addressRepository.findAll();
       List<AddressDTO> addressDTO = allAddresses.stream().
               map(addresses -> modelMapper.map(addresses, AddressDTO.class)).collect(Collectors.toList());
        return addressDTO;
    }

    @Override
    public AddressDTO getAddressById(Long addressesId) {
        Address getAddress = addressRepository.findById(addressesId).
                orElseThrow(()-> new ResourceNotFoundException("Product", "productId",addressesId));
        AddressDTO addressDTO = modelMapper.map(getAddress,AddressDTO.class);
        return  addressDTO;
    }

    @Override
    public List<AddressDTO> getAddressByUser(Users users) {
        List<Address> addressList = users.getAddresses();


         List<AddressDTO> addressDto = addressList.stream().map((element) -> modelMapper.
                 map(element, AddressDTO.class)).toList();
   return  addressDto;
    }

    @Override
    public AddressDTO updateAddressById(Long addressId, AddressDTO addressDTO) {

        Address addressFromDb = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId",addressId));
        Address address = modelMapper.map(addressDTO, Address.class);
        addressFromDb.setCountry(address.getCountry());
        addressFromDb.setState(address.getState());
        addressFromDb.setCity(address.getCity());
        addressFromDb.setStreet(address.getStreet());
        addressFromDb.setBuildingName(address.getBuildingName());
        addressFromDb.setZipcode(address.getZipcode());
        Address savedAddress = addressRepository.save(addressFromDb);
        AddressDTO savedAddressDto = modelMapper.map(savedAddress,AddressDTO.class);
        return savedAddressDto;
    }

    @Override
    public AddressDTO deleteAddressById(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address","addressId",addressId));
        addressRepository.delete(address);
        AddressDTO deletedAddress = modelMapper.map(address,AddressDTO.class);


        return deletedAddress;
    }
}
