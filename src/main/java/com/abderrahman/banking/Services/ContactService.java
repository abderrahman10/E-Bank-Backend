package com.abderrahman.banking.Services;

import com.abderrahman.banking.DTOs.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto>{
    List<ContactDto> findAllByUserId(Integer userId);
}
