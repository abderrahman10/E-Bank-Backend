package com.abderrahman.banking.Services;

import com.abderrahman.banking.DTOs.TransactionSumDetails;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface StatisticsService {
    List<TransactionSumDetails> findSumTransactionByDate(LocalDate startDate, LocalDate endDate, Integer userID);
    BigDecimal getAccountBalance(Integer userId); //le solde de user
    BigDecimal  highestTransfert(Integer userId);
    BigDecimal  highestDeposit(Integer userId);


}
