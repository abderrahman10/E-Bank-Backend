package com.abderrahman.banking.Controllers;

import com.abderrahman.banking.DTOs.AddressDto;
import com.abderrahman.banking.DTOs.UserDto;
import com.abderrahman.banking.Services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;


    @PostMapping("/")
    private ResponseEntity<Integer> save(@RequestBody AddressDto addressDto){
        return  ResponseEntity.ok(service.save(addressDto));
    }



    @GetMapping("/")
    public  ResponseEntity<List<AddressDto>>findAll(){
        return  ResponseEntity.ok(service.findAll());
    }

    @GetMapping ("/{address-id}")
    public  ResponseEntity<AddressDto>findById(@PathVariable("address-id") Integer IdAddress){
        return  ResponseEntity.ok(service.findById(IdAddress));
    }




    @DeleteMapping ("/{address-id}")
    public  ResponseEntity<Void> Delete(@PathVariable("address-id")Integer IdAddress){
        service.delete(IdAddress);
        return  ResponseEntity.accepted().build();

    }
}
