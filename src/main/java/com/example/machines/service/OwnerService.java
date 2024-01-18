package com.example.machines.service;

import com.example.machines.model.Machine;
import com.example.machines.model.OfferByOwner;
import com.example.machines.model.Owner;
import com.example.machines.repository.MachineRepository;
import com.example.machines.repository.OfferByOwnerRepository;
import com.example.machines.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


import static com.example.machines.model.OfferByOwner.createOfferByOwner;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final MachineRepository machineRepository;
    private final OfferByOwnerRepository offerByOwnerRepository;


    @Autowired
    public OwnerService(OwnerRepository ownerRepository, MachineRepository machineRepository, OfferByOwnerRepository offerByOwnerRepository) {
        this.ownerRepository = ownerRepository;
        this.machineRepository = machineRepository;
        this.offerByOwnerRepository = offerByOwnerRepository;
    }

//    public OfferByOwner createOffer(Owner owner, Date startAvailabilityDate, Date endAvailabilityDate, String machineName) {
//        Machine machine = machineRepository.findMachineByNameAndOwner(machineName, owner);
//       //ownerRepository.save(owner);
//        OfferByOwner offer = createOfferByOwner(owner, startAvailabilityDate, endAvailabilityDate, machine);
//
//        offerByOwnerRepository.save(offer);
//        return offer;
//    }


}
