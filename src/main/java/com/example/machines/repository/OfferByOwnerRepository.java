package com.example.machines.repository;


import com.example.machines.model.OfferByOwner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferByOwnerRepository extends JpaRepository<OfferByOwner, Long> {


    OfferByOwner save(OfferByOwner offer);
   // OfferByOwner update(OfferByOwner offer);

    OfferByOwner findOfferById(long id);


    @Modifying
    @Query("DELETE FROM OfferByOwner o WHERE o.machine.id = :machineId")
    void deleteOfferByMachineId(@Param("machineId") long machineId);

    List<OfferByOwner> findAll();

    @Query("select o from OfferByOwner o where o.owner.id= :ownerId and o.status='ON_AUCTION'")
    List<OfferByOwner> findAllOnAuctionOfferByOwner(@Param("ownerId") long ownerId);

//    @Query("select o from OfferByOwner o where o.owner.id= :ownerId and o.status='ON_AUCTION'")
//    List<OfferByOwner> findResponsesByOfferId(@Param("ownerId") long ownerId);

  //  List<OfferByOwner> findAllOnAuctionOfferByOwner(@Param("ownerId") long ownerId);
}