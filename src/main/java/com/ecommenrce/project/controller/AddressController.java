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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
