package com.abderrahman.banking.Controllers;

import com.abderrahman.banking.DTOs.UserDto;
import com.abderrahman.banking.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

private  final UserService service;


@PostMapping("/")
 private ResponseEntity<Integer>save( @RequestBody UserDto userDto){
  return  ResponseEntity.ok(service.save(userDto));
 }



@GetMapping ("/")
public  ResponseEntity<List<UserDto>>findAll(){
    return  ResponseEntity.ok(service.findAll());
}

    @GetMapping ("/{user-id}")
public  ResponseEntity<UserDto>findById(@PathVariable("user-id") Integer IdUser){
    return  ResponseEntity.ok(service.findById(IdUser));
}

    @PatchMapping ("/validate/{user-id}")
    public  ResponseEntity<Integer>ValidateAccount(@PathVariable("user-id") Integer IdUser){
        return  ResponseEntity.ok(service.validateAccount(IdUser));
    }

    @PatchMapping ("/invalidate/{user-id}")
    public  ResponseEntity<Integer>InValidateAccount(@PathVariable("user-id") Integer IdUser){
        return  ResponseEntity.ok(service.invalidateAccount(IdUser));
    }


    @DeleteMapping ("/{user-id}")
    public  ResponseEntity<Void> Delete(@PathVariable("user-id")Integer IdUser){
        service.delete(IdUser);
        return  ResponseEntity.accepted().build();

    }



}
