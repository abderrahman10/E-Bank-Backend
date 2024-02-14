package com.abderrahman.banking.DTOs;

import com.abderrahman.banking.models.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private  Integer id;

    @NotNull(message = "le prenom  ne doit pas etre null ")
    @NotEmpty(message = "le prenom  ne doit pas etre vide ")
    @NotBlank(message = "le prenom  ne doit pas etre vide ")
    private  String firstname;
    @NotNull(message = "le nom  ne doit pas etre null ")
    @NotEmpty(message = "le nom  ne doit pas etre vide ")
    @NotBlank(message = "le nom  ne doit pas etre vide ")
    private  String lastname;
    @NotNull(message = "email ne doit pas etre null ")
    @NotEmpty(message = "email ne doit pas etre vide ")
    @NotBlank(message = "email ne doit pas etre vide ")
    @Email(message = "email n'est pas conforme ")
    private  String email;
    @NotNull(message = "password ne doit pas etre null ")
    @NotEmpty(message = "password ne doit pas etre vide ")
    @NotBlank(message = "password ne doit pas etre vide ")
    @Size(min = 8,max = 16,message = "password must be between 8 and 16 character  ")
    private  String password;

    public  static  UserDto fromEntity(User user){
        //null check
        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
    public  static  User toEntity(UserDto user){
        //null check
        return User.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }


}
