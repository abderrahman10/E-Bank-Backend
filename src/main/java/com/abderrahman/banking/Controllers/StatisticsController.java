package com.abderrahman.banking.Controllers;

import com.abderrahman.banking.DTOs.TransactionSumDetails;
import com.abderrahman.banking.Services.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private  final StatisticsService service ;

    @GetMapping("/sum-by-date/{user_id}")
    public ResponseEntity <List<TransactionSumDetails>> findSumTransactionByDate(
         @RequestParam("startDate")@DateTimeFormat(pattern = "yyyy--MM--dd") LocalDate startDate,
         @RequestParam("endDate")@DateTimeFormat(pattern = "yyyy--MM--dd") LocalDate endDate,
         @PathVariable("user_id") Integer userID){
         return  ResponseEntity.ok(service.findSumTransactionByDate(startDate,endDate,userID));

    };

    @GetMapping("/account-balance/{user_id}")
    public ResponseEntity<BigDecimal>  getAccountBalance(@PathVariable("user_id")Integer userId){

        return ResponseEntity.ok(service.getAccountBalance(userId));
    };
    @GetMapping("/highest-transfert/{user_id}")
    public ResponseEntity<BigDecimal>   highestTransfert( @PathVariable("user_id")Integer userId){
        return ResponseEntity.ok(service.highestTransfert(userId));
    };
    @GetMapping("/highest-deposit/{user_id}")
    public ResponseEntity<BigDecimal>   highestDeposit( @PathVariable("user_id")Integer userId){
        return ResponseEntity.ok(service.highestDeposit(userId));
    };


}
