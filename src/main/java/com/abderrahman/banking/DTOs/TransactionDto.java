package com.abderrahman.banking.DTOs;

import com.abderrahman.banking.models.Account;
import com.abderrahman.banking.models.Transaction;
import com.abderrahman.banking.models.TransactionType;
import com.abderrahman.banking.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
    private  Integer id;
    @Positive

    private BigDecimal amount;
    private TransactionType type;
    private  String destinationIban;
    private LocalDate transactionDate;
    private  Integer userId;

    public  static TransactionDto fromEntity(Transaction transaction){   //transformer un account to an accountDTO
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .transactionDate(transaction.getTransactionDate())
                .destinationIban(transaction.getDestinationIban())
                .userId(transaction.getUser().getId())
                .build();
    }
    public  static Transaction fromEntity(TransactionDto transactionDTO){   //transformer un account to an accountDTO
        return Transaction.builder()
                .id(transactionDTO.getId())
                .amount(transactionDTO.getAmount())
                .type(transactionDTO.getType())
                .transactionDate(LocalDate.now())
                .destinationIban(transactionDTO.getDestinationIban())
                .user(User.builder().id(transactionDTO.getUserId()).build())
                .build();
    }

}
