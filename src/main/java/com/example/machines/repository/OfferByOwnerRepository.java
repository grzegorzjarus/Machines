package com.example.machines.repository;


import com.example.machines.model.OfferByOwner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferByOwnerRepository extends JpaRepository<OfferByOwner, Long> {


    OfferByOwner save(OfferByOwner offer);


    @Modifying
    @Query("DELETE FROM OfferByOwner o WHERE o.machine.id = :machineId")
    void deleteOfferByMachineId(@Param("machineId") long machineId);
}