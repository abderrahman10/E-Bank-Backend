package com.abderrahman.banking.DTOs;

import com.abderrahman.banking.models.Address;
import com.abderrahman.banking.models.Contact;
import com.abderrahman.banking.models.User;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDto {
    private  Integer id;
    private  String firstname;
    private  String lastname;
    private  String email;
    private  String iban;
    private  Integer userID;

    public  static ContactDto fromEntity(Contact contact){   //transformer un account to an accountDTO
        return ContactDto.builder()
                .id(contact.getId())
                .firstname(contact.getFirstname())
                .lastname(contact.getLastname())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .userID(contact.getUser().getId())
                .build();
    }
    public  static Contact toEntity(ContactDto contactDto){   //transformer un account to an accountDTO
        return Contact.builder()
                .id(contactDto.getId())
                .firstname(contactDto.getFirstname())
                .lastname(contactDto.getLastname())
                .email(contactDto.getEmail())
                .iban(contactDto.getIban())
                .user(User.builder().id(contactDto.getUserID()).build())
                .build();

    }
}
