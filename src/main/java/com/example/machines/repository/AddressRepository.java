package com.example.machines.repository;

import com.example.machines.model.Address;
import com.example.machines.model.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    //  Machine findMachineById(long id);
    Address save(Address address);
}