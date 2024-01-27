package com.example.machines.service;

import com.example.machines.model.Machine;
import com.example.machines.model.MachineStatus;
import com.example.machines.model.OfferByOwner;
import com.example.machines.model.ResponseByRenter;
import com.example.machines.repository.MachineRepository;
import com.example.machines.repository.OfferByOwnerRepository;
import com.example.machines.repository.OwnerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferByOwnerService {

    private final OfferByOwnerRepository offerByOwnerRepository;
    private final OwnerRepository ownerRepository;
    private final MachineRepository machineRepository;

    public OfferByOwnerService(OfferByOwnerRepository offerByOwnerRepository, OwnerRepository ownerRepository, MachineRepository machineRepository) {
        this.offerByOwnerRepository = offerByOwnerRepository;
        this.ownerRepository = ownerRepository;
        this.machineRepository = machineRepository;
    }
    @Transactional
    public void createNewOffer(OfferByOwner offer , String email, long machineId) {
        List<ResponseByRenter> responses = new ArrayList<>();
        offer.setResponses(responses);
        System.out.println("Maszyna o ID: " +machineId);
        System.out.println("Oferta z kontrolera" + offer);
        System.out.println("Email z kontrolera" + email);


        offer.setOwner(ownerRepository.findOwnerByEmail(email));
        Machine machine = machineRepository.findMachineById(machineId);
        offer.setMachine(machine);
        machine.setStatus(MachineStatus.ON_AUCTION);
        machineRepository.save(machine);
        System.out.println("Oferta: "+offer);

        offerByOwnerRepository.save(offer);
    }

    public void deleteOffer(long machineId) {
        machineRepository.findMachineById(machineId).setStatus(MachineStatus.FREE);
        offerByOwnerRepository.deleteOfferByMachineId(machineId);

    }

    public List<OfferByOwner> getAllOffer(){
        return offerByOwnerRepository.findAll();
    }
}
