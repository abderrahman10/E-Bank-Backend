package com.abderrahman.banking.Controllers;

import com.abderrahman.banking.DTOs.ContactDto;
import com.abderrahman.banking.Services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService service;


    @PostMapping("/")
    private ResponseEntity<Integer> save(@RequestBody ContactDto contactDto){
        return  ResponseEntity.ok(service.save(contactDto));
    }



    @GetMapping("/")
    public  ResponseEntity<List<ContactDto>>findAll(){
        return  ResponseEntity.ok(service.findAll());
    }

    @GetMapping ("/{contact-id}")
    public  ResponseEntity<ContactDto>findById(@PathVariable("contact-id") Integer contactId){
        return  ResponseEntity.ok(service.findById(contactId));
    }




    @DeleteMapping ("/{contact-id}")
    public  ResponseEntity<Void> Delete(@PathVariable("contact-id")Integer ContactId){
        service.delete(ContactId);
        return  ResponseEntity.accepted().build();

    }


    @GetMapping ("/users/{user-id}")
    public  ResponseEntity<List<ContactDto>>findAllByUserId(@PathVariable("user-id") Integer UserId){
        return  ResponseEntity.ok(service.findAllByUserId(UserId));
    }

}
