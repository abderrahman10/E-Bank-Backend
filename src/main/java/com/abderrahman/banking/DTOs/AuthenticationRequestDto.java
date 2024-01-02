package com.abderrahman.banking.DTOs;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String email;
    private String password;
}
