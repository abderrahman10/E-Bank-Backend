package com.abderrahman.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction extends  AbstractEntity {

    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private  TransactionType type;
    private  String destinationIban;
    @Column(updatable = false)
    private LocalDate transactionDate;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private  User user;


}
