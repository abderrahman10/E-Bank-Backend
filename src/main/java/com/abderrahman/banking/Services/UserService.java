package com.abderrahman.banking.Services;

import com.abderrahman.banking.DTOs.AuthenticationRequestDto;
import com.abderrahman.banking.DTOs.AuthenticationResponseDto;
import com.abderrahman.banking.DTOs.UserDto;

public interface UserService extends  AbstractService<UserDto>{

Integer validateAccount(Integer id );
Integer invalidateAccount(Integer id );

    AuthenticationResponseDto register(UserDto user);

    AuthenticationResponseDto authenticate(AuthenticationRequestDto request);
}
