package com.example.machines.repository;

import com.example.machines.model.OfferByOwner;
import com.example.machines.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findOwnerById(long id);
    //void save(OfferByOwner offer);
    Owner save(Owner owner);

}
