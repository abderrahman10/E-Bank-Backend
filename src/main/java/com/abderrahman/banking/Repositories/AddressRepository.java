package com.abderrahman.banking.Repositories;

import com.abderrahman.banking.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
