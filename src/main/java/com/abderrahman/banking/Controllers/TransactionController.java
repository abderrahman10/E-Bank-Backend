package com.abderrahman.banking.Controllers;

import com.abderrahman.banking.DTOs.AccountDto;
import com.abderrahman.banking.DTOs.TransactionDto;
import com.abderrahman.banking.Services.AccountService;
import com.abderrahman.banking.Services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService service;


    @PostMapping("/")
    private ResponseEntity<Integer> save(@RequestBody TransactionDto transactionDto){
        return  ResponseEntity.ok(service.save(transactionDto));
    }



    @GetMapping("/")
    public  ResponseEntity<List<TransactionDto>>findAll(){
        return  ResponseEntity.ok(service.findAll());
    }

    @GetMapping ("/{transaction-id}")
    public  ResponseEntity<TransactionDto>findById(@PathVariable("transaction-id") Integer idTransaction){
        return  ResponseEntity.ok(service.findById(idTransaction));
    }

    @GetMapping ("/users/{user-id}")
    public  ResponseEntity<List<TransactionDto>>findByAllByUserId(@PathVariable("user-id") Integer UserId){
        return  ResponseEntity.ok(service.findAllByUserId(UserId));
    }



    @DeleteMapping ("/{transaction-id}")
    public  ResponseEntity<Void> Delete(@PathVariable("transaction-id")Integer idTransaction){
        service.delete(idTransaction);
        return  ResponseEntity.accepted().build();

    }
}
