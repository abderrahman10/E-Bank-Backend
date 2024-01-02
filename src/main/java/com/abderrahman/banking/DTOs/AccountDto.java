package com.abderrahman.banking.DTOs;

import com.abderrahman.banking.models.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AccountDto {
    private Integer id;
    private  String iban;
    private UserDto user;


    public  static AccountDto fromEntity(Account account){   //transformer un account to an accountDTO
        return AccountDto.builder()
                .id(account.getId())
                .iban(account.getIban())
                .user(UserDto.fromEntity(account.getUser()))
                .build();
    }
    public  static Account ToEntity(AccountDto account){
        return Account.builder()
                .id(account.getId())
                .iban(account.getIban())
                .user(UserDto.toEntity(account.getUser()))
                .build();
    }

}
