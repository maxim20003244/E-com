package com.ecommenrce.project.controller;

import com.ecommenrce.project.dto.AddressDTO;
import com.ecommenrce.project.model.Users;
import com.ecommenrce.project.service.AddressService;
import com.ecommenrce.project.util.AuthUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class AddressController {
    @Autowired
    private AddressService addressService;
    @Autowired
    AuthUtil authUtil;
    @PostMapping ("/addresses")
    public ResponseEntity<AddressDTO> createAddresses(@Valid @RequestBody AddressDTO addressDTO){
        Users user = authUtil.loggedInUser();
      AddressDTO saveAddressDTO =  addressService.createAddress(user,addressDTO);
        return new ResponseEntity<>(saveAddressDTO, HttpStatus.OK);
    }

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAllAddresses(){
        List<AddressDTO> getAll = addressService.getAllAddresses();
        return new ResponseEntity<>(getAll,HttpStatus.OK);
    }
    @GetMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> getAllAddresses(@PathVariable Long addressId){
     AddressDTO getAddress = addressService.getAddressById(addressId);
        return new ResponseEntity<>(getAddress,HttpStatus.OK);
    }

    @GetMapping ("/users/addresses")
    public ResponseEntity<List<AddressDTO>> createAddresses(){
        Users user = authUtil.loggedInUser();
        List<AddressDTO >getAddress = addressService.getAddressByUser(user);

        return new ResponseEntity<>(getAddress, HttpStatus.OK);
    }

    @PutMapping("/addresses/{addressId}")
    public ResponseEntity<AddressDTO> updateAddressById(@PathVariable Long addressId,
                                                         @RequestBody AddressDTO addressDTO){
       AddressDTO updateAddress = addressService.updateAddressById(addressId,addressDTO);
       return new ResponseEntity<>(updateAddress,HttpStatus.OK);
    }
    @DeleteMapping("/address/delete/{addressId}")
    public ResponseEntity<AddressDTO> deleteAddressById(@PathVariable Long addressId){
        AddressDTO deleteAddress = addressService.deleteAddressById(addressId);
        return new ResponseEntity<>(deleteAddress,HttpStatus.OK);
    }


}
