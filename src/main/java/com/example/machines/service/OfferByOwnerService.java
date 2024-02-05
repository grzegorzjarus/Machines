package com.example.machines.service;

import com.example.machines.model.Machine;
import com.example.machines.model.MachineStatus;
import com.example.machines.model.OfferByOwner;
import com.example.machines.model.ResponseByRenter;
import com.example.machines.repository.MachineRepository;
import com.example.machines.repository.OfferByOwnerRepository;
import com.example.machines.repository.OwnerRepository;
import com.example.machines.repository.ResponseByRenterRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OfferByOwnerService {

    private final OfferByOwnerRepository offerByOwnerRepository;
    private final OwnerRepository ownerRepository;
    private final MachineRepository machineRepository;
    private final ResponseByRenterRepository responseByRenterRepository;

    public OfferByOwnerService(OfferByOwnerRepository offerByOwnerRepository, OwnerRepository ownerRepository, MachineRepository machineRepository, ResponseByRenterRepository responseByRenterRepository) {
        this.offerByOwnerRepository = offerByOwnerRepository;
        this.ownerRepository = ownerRepository;
        this.machineRepository = machineRepository;
        this.responseByRenterRepository = responseByRenterRepository;
    }
    @Transactional
    public void createNewOffer(OfferByOwner offer , String email, long machineId) {
        List<ResponseByRenter> responses = new ArrayList<>();
        offer.setResponses(responses);
        System.out.println("Maszyna o ID: " +machineId);
        //System.out.println("Oferta z serwisu " + offer);
        System.out.println("Email z serwisu " + email);

        String formattedEmail = email.substring(1, email.length() - 1);


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

    public List<ResponseByRenter> getOfferResponses(long offerId) {
        OfferByOwner offer = offerByOwnerRepository.findOfferById(offerId);
        System.out.println(offer.getOwner().getEmail());
        System.out.println(offer.getOwner().getName());
        System.out.println(offer.getOwner().getCompanyName());
        //System.out.println(offer.getResponses());
      return responseByRenterRepository.findAllByOfferId(offerId);

    }
}
