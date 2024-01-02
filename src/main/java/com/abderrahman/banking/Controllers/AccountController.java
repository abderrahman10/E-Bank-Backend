package com.abderrahman.banking.Controllers;

import com.abderrahman.banking.DTOs.AccountDto;
import com.abderrahman.banking.DTOs.AddressDto;
import com.abderrahman.banking.Services.AccountService;
import com.abderrahman.banking.Services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService service;


    @PostMapping("/")
    private ResponseEntity<Integer> save(@RequestBody AccountDto accountDto){
        return  ResponseEntity.ok(service.save(accountDto));
    }



    @GetMapping("/")
    public  ResponseEntity<List<AccountDto>>findAll(){
        return  ResponseEntity.ok(service.findAll());
    }

    @GetMapping ("/{account-id}")
    public  ResponseEntity<AccountDto>findById(@PathVariable("account-id") Integer idAccount){
        return  ResponseEntity.ok(service.findById(idAccount));
    }




    @DeleteMapping ("/{account-id}")
    public  ResponseEntity<Void> Delete(@PathVariable("account-id")Integer idAccount){
        service.delete(idAccount);
        return  ResponseEntity.accepted().build();

    }
}
