package com.abderrahman.banking.DTOs;

import com.abderrahman.banking.models.Account;
import com.abderrahman.banking.models.Address;
import com.abderrahman.banking.models.User;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AddressDto {

    private  Integer id;
    private  String street;
    private  Integer houseNumber;
    private  Integer zipCode;
    private String  city;
    private  String country;
    private  Integer userId; // pour modifier address j'ai besoin de id de l'utilisateur
                               // (Address a un relation avec user)


    public  static AddressDto fromEntity(Address address){   //transformer un account to an accountDTO
        return AddressDto.builder()
                .id(address.getId())
                .street(address.getStreet())
                .houseNumber(address.getHouseNumber())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .country(address.getCountry())
                .userId(address.getUser().getId())
                .build();
    }
    public  static Address toEntity(AddressDto addressDto){   //transformer un accountDTO to an account
        return Address.builder()
                .id(addressDto.getId())
                .street(addressDto.getStreet())
                .houseNumber(addressDto.getHouseNumber())
                .zipCode(addressDto.getZipCode())
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .user(User.builder().id(addressDto.getUserId()).build())
                .build();
    }
}
