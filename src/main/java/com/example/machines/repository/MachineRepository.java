package com.example.machines.repository;

import com.example.machines.model.Machine;

import com.example.machines.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Long> {

    //  Machine findMachineById(long id);
    Machine save(Machine machine);

    Machine findMachineByNameAndOwner(String name, Owner owner);
}
