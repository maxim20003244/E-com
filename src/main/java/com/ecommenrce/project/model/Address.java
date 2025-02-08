package com.ecommenrce.project.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;
    @NotBlank
    @Size(min = 5,message = "Street name must be at less 5 characters")
    private String street;
    @NotBlank
    @Size(min = 5,message = "Building  name must be at less 5 characters")
    String buildingName;
    @NotBlank
    @Size(min = 4,message = "Street name must be at less 4 characters")
    String city;
    @NotBlank
    @Size(min = 2,message = "Street name must be at less 2 characters")
    String state;
    @NotBlank
    @Size(min = 2,message = "Street name must be at less 2 characters")
    String country;
    @NotBlank
    @Size(min = 5,message = "Zipcode name must be at less 5 characters")
    private String zipcode;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Address(String street, String buildingName, String city, String state, String country) {
        this.street = street;
        this.buildingName = buildingName;
        this.city = city;
        this.state = state;
        this.country = country;
    }
}
