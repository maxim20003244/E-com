package com.ecommenrce.project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private Long addressId;
    private String street;
    String buildingName;
    String city;
    String state;
    String country;
    private String zipcode;
}
