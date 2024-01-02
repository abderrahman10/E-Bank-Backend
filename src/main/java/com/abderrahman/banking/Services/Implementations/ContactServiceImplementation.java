package com.abderrahman.banking.Services.Implementations;

import com.abderrahman.banking.DTOs.ContactDto;
import com.abderrahman.banking.Exceptions.ObjectValidationException;
import com.abderrahman.banking.Repositories.ContactRepository;
import com.abderrahman.banking.Services.ContactService;
import com.abderrahman.banking.Validators.ObjectsValidators;
import com.abderrahman.banking.models.Contact;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContactServiceImplementation implements ContactService {
 private  final ContactRepository contactRepository ;
 private  final ObjectsValidators<ContactDto> validator;
    @Override
    public Integer save(ContactDto dto) {
        validator.validate(dto);
        Contact contact=ContactDto.toEntity(dto);
        return contactRepository.save(contact).getId();
    }

    @Override
    public List<ContactDto> findAll() {
        return contactRepository
                .findAll()
                .stream()
                .map(ContactDto::fromEntity)
                .collect(Collectors.toList());

    }

    @Override
    public ContactDto findById(Integer id) {
        return contactRepository.findById(id)
                .map(ContactDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException("no contact wad found  with  ID "+ id));
    }

    @Override
    public void delete(Integer id) {
  contactRepository.deleteById(id);
    }

    @Override
    public List<ContactDto> findAllByUserId(Integer userId) { //envoie la listes des contact dans le carnet d'adresse d'un user selectionné
        return contactRepository.findAllByUserId(userId)
                .stream()
                .map(ContactDto::fromEntity).
                collect(Collectors.toList());
    }
}
