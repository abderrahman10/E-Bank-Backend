package com.abderrahman.banking.Services.Implementations;

import com.abderrahman.banking.DTOs.AccountDto;
import com.abderrahman.banking.Exceptions.OperationNonPermittedException;
import com.abderrahman.banking.Repositories.AccountRepository;
import com.abderrahman.banking.Services.AccountService;
import com.abderrahman.banking.Validators.ObjectsValidators;
import com.abderrahman.banking.models.Account;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.iban4j.CountryCode;
import org.iban4j.Iban;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountServiceImplementation implements AccountService {

    private  final AccountRepository accountRepository;
    private  final ObjectsValidators<AccountDto> validator;
    @Override
    public Integer save(AccountDto dto) {
        // bloquer  les modifications des comptes car iban cannot be changed

        validator.validate(dto);
        Account account=AccountDto.ToEntity(dto);
        boolean UserHasAlreadyAccount=accountRepository.findByUserId(account.getUser().getId()).isPresent();
        if (UserHasAlreadyAccount && account.getUser().isActive() ){

            throw  new OperationNonPermittedException(
                    "the selected user had already an active account ",
                    "Create Account ",
                    "AccountService",
                    "AccountCreation"
            );
        }
//  generate random Iban
        if(dto.getId()==null){
            account.setIban(GenerateRandomIban());
        }

        return accountRepository.save(account).getId();
    }

    @Override
    public List<AccountDto> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(AccountDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto findById(Integer id) {
        return accountRepository.findById(id)
                .map(AccountDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(" no account was found with th ID : " +id));
    }

    @Override
    public void delete(Integer id) {

        accountRepository.deleteById(id);
    }
    private  String GenerateRandomIban(){
        // generateIban
      String iban= Iban.random(CountryCode.DE).toFormattedString();
        //check id the iban exist
         boolean IbanExists= accountRepository.findByIban(iban).isPresent()  ;
        // if exist generate new random iban
        if(IbanExists){
            GenerateRandomIban();
        }
        // if not exist return generated iban
        return  iban;
    }
}
