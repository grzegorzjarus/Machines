package com.example.machines.repository;


import com.example.machines.model.OfferByOwner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferByOwnerRepository extends JpaRepository<OfferByOwner, Long> {

    //OfferByOwner findOwnerByOwnerById(long id);
    OfferByOwner save(OfferByOwner offer);
}