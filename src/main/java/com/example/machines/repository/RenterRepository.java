package com.example.machines.repository;

import com.example.machines.model.Owner;
import com.example.machines.model.Renter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RenterRepository extends JpaRepository<Renter, Long> {

    Renter findRenterByEmail(String email);
}
