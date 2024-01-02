package com.abderrahman.banking.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="_user" )
public class User extends  AbstractEntity implements UserDetails {

  private  String firstname;
  private  String lastname;
  @Column(unique = true)
  private  String email;
  private  String password;


  private  boolean active;

@OneToMany(mappedBy = "user")
private  List<Transaction> transactions;

  @OneToMany(mappedBy = "user")
  List<Contact> contacts;


  @OneToOne
  private Account account;

 @OneToOne
  private Address address ;



    @Override  //les roles de l'utilisateur
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
