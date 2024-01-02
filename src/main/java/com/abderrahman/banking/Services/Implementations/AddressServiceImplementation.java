package com.abderrahman.banking.Services.Implementations;

import com.abderrahman.banking.DTOs.AddressDto;
import com.abderrahman.banking.Repositories.AddressRepository;
import com.abderrahman.banking.Services.AddressService;
import com.abderrahman.banking.Validators.ObjectsValidators;
import com.abderrahman.banking.models.Address;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImplementation implements AddressService {
    private  final AddressRepository addressRepository;
    private ObjectsValidators<AddressDto> validator;

    @Override
    public Integer save(AddressDto dto) {
        validator.validate(dto);
        Address address = AddressDto.toEntity(dto);

        return addressRepository.save(address).getId();
    }

    @Override
    public List<AddressDto> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(AddressDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public AddressDto findById(Integer id) {
        return addressRepository.findById(id)
                .map(AddressDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(" No Address Found with the ID :"+id));
    }

    @Override
    public void delete(Integer id) {
        addressRepository.deleteById(id);

    }
}
