package com.abderrahman.banking.Repositories;


import com.abderrahman.banking.DTOs.TransactionSumDetails;
import com.abderrahman.banking.models.Transaction;
import com.abderrahman.banking.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {




    List<Transaction> findAllByUserId(Integer userId);

    @Query("select sum(t.amount) from Transaction t where t.user.id = :userId")
    BigDecimal findAccountBalance(Integer userId);


    @Query("select max(abs(t.amount)) as amount from Transaction t where t.user.id = :userId and  t.user.id = :userId and t.type = :transactiontype group by t.createdDate ")

    BigDecimal findHighestAmountByTransactionType(Integer userId, TransactionType transactiontype) ;

    @Query("select t.transactionDate as transactionDate, sum(t.amount) as amount from Transaction t where t.user.id = :userId and t.createdDate "
            + "between :start and :end "
            + "group by t.transactionDate")
    List<TransactionSumDetails> findSumTransactionsByDate(LocalDateTime start, LocalDateTime end, Integer userId);
}
