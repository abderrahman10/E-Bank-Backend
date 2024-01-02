package com.abderrahman.banking.Services.Implementations;

import com.abderrahman.banking.DTOs.TransactionDto;
import com.abderrahman.banking.Repositories.TransactionRepository;
import com.abderrahman.banking.Services.TransactionService;
import com.abderrahman.banking.Validators.ObjectsValidators;
import com.abderrahman.banking.models.Transaction;
import com.abderrahman.banking.models.TransactionType;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TransactionServiceImplementation implements TransactionService {
    private  final TransactionRepository transactionRepository;
    private  final ObjectsValidators<TransactionDto>validator;
    @Override
    public Integer save(TransactionDto dto) {
        validator.validate(dto);
        Transaction transaction= TransactionDto.fromEntity(dto);
        BigDecimal transactionMultiplier= BigDecimal.valueOf(getTransactionMultiplier(transaction.getType()));
       BigDecimal amount= transaction.getAmount().multiply(transactionMultiplier);
       transaction.setAmount(amount);
        return transactionRepository.save(transaction).getId();
    }

    @Override
    public List<TransactionDto> findAll() {

        return transactionRepository.findAll()
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDto findById(Integer id) {
        return transactionRepository
                .findById(id)
                .map(TransactionDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("No Transaction was founded with th ID : "+id));

    }

    @Override
    public void delete(Integer id) {
transactionRepository.deleteById(id);
    }

    private  int getTransactionMultiplier(TransactionType type){
        return  TransactionType.TRANSFERT==type ? -1 : 1 ;
    }

    @Override
    public List<TransactionDto> findAllByUserId(Integer userId) {
        return transactionRepository.findAllByUserId(userId)
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }
}
