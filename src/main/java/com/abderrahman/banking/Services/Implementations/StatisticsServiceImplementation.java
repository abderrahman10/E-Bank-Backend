package com.abderrahman.banking.Services.Implementations;

import com.abderrahman.banking.DTOs.TransactionSumDetails;
import com.abderrahman.banking.Repositories.TransactionRepository;
import com.abderrahman.banking.Services.StatisticsService;
import com.abderrahman.banking.models.TransactionType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class StatisticsServiceImplementation implements StatisticsService {
    private  final TransactionRepository transactionRepository;

    @Override
    public List<TransactionSumDetails>  findSumTransactionByDate(LocalDate startDate, LocalDate endDate, Integer userID) {
        LocalDateTime start = LocalDateTime.of(startDate, LocalTime.of(0,0,0));
        LocalDateTime end = LocalDateTime.of(startDate, LocalTime.of(23,59,59));
        return transactionRepository.findSumTransactionsByDate( start,end,userID);
    }

    @Override
    public BigDecimal getAccountBalance(Integer userId) {
        return transactionRepository.findAccountBalance(userId);
    }

    @Override
    public BigDecimal highestTransfert(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId,TransactionType.TRANSFERT);
    }


    @Override
    public BigDecimal highestDeposit(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId,TransactionType.DEPOSIT);
    }
}
