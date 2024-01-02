package com.abderrahman.banking.Services;

import com.abderrahman.banking.DTOs.TransactionDto;

import java.util.List;

public interface TransactionService extends AbstractService<TransactionDto>{
    List<TransactionDto> findAllByUserId(Integer userId);
}
