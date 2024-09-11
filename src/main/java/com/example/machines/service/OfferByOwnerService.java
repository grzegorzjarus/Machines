package com.example.machines.service;

import com.example.machines.model.*;
import com.example.machines.repository.MachineRepository;
import com.example.machines.repository.OfferByOwnerRepository;
import com.example.machines.repository.OwnerRepository;
import com.example.machines.repository.ResponseByRenterRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
    public void createNewOffer(OfferByOwner offer, String email, long machineId) {
        List<ResponseByRenter> responses = new ArrayList<>();
        offer.setResponses(responses);
        System.out.println("Maszyna o ID: " + machineId);
        //System.out.println("Oferta z serwisu " + offer);
        System.out.println("Email z serwisu " + email);

        String formattedEmail = email.substring(1, email.length() - 1);



        offer.setOwner(ownerRepository.findOwnerByEmail(email));
        Machine machine = machineRepository.findMachineById(machineId);
        offer.setStatus(OfferStatus.ON_AUCTION);
        machine.setStatus(MachineStatus.ON_AUCTION);
        machineRepository.save(machine);
        offer.setMachine(machine);
        System.out.println("Oferta: " + offer);


        offerByOwnerRepository.save(offer);
    }

    @Transactional
    public void deleteOffer(long machineId) {
        machineRepository.findMachineById(machineId).setStatus(MachineStatus.FREE);
        OfferByOwner offer = offerByOwnerRepository.findOfferByMachineId(machineId);
        System.out.println(offer.getDeliveryPrice());
        List<ResponseByRenter> responses = responseByRenterRepository.findAllByOfferId(offer.getId());
        //responseByRenterRepository.deleteAll(offer.getResponses());
        //offer.getResponses().clear();
        // offer.getResponses()
        // offerByOwnerRepository.save(offer);
        //  offerByOwnerRepository.delete(offer);
        //List<ResponseByRenter> responses = offer.getResponses();

        for (ResponseByRenter response : responses) {
            System.out.println(response.getPrice());
            responseByRenterRepository.delete(response);
        }

        //offerByOwnerRepository.save(offer);

        //offerByOwnerRepository.deleteOfferByMachineId(machineId);
        offerByOwnerRepository.delete(offer);

    }

    public List<OfferByOwner> getAllOffer() {
        return offerByOwnerRepository.findAll();
    }

    public List<ResponseByRenter> getOfferResponses(long offerId) {
//        OfferByOwner offer = offerByOwnerRepository.findOfferById(offerId);
//        System.out.println(offer.getOwner().getEmail());
//        System.out.println(offer.getOwner().getName());
//        System.out.println(offer.getOwner().getCompanyName());
        //System.out.println(offer.getResponses());
        return responseByRenterRepository.findAllByOfferId(offerId);

    }

    public List<OfferByOwner> getAllOnAuctionOfferByOwner(String email) {
        //String formattedEmail = email.substring(10, email.length() - 2);
        String formattedEmail = email.substring(1, email.length() - 1);
        System.out.println("email from active " + email);
        System.out.println("Formatted email from active " + formattedEmail);
        long id = ownerRepository.findOwnerByEmail(formattedEmail).getId();

        List<OfferByOwner> list = offerByOwnerRepository.findAllOnAuctionOfferByOwner(id);

        return list;

//       return offerByOwnerRepository.findAllOnAuctionOfferByOwner(id);
    }

    public String acceptResponse(long offerId, long responseId) {
        OfferByOwner offer = offerByOwnerRepository.findOfferById(offerId);
        offer.setStatus(OfferStatus.RENTER);
        offer.getMachine().setStatus(MachineStatus.RENTER);
        ResponseByRenter response = responseByRenterRepository.findResponseById(responseId);


        for (ResponseByRenter respons : offer.getResponses()) {
            if (respons.getId() == responseId){
                response.setStatus(ResponseByRenterStatus.RENTER);
                responseByRenterRepository.save(response);
                continue;
            }
            respons.setStatus(ResponseByRenterStatus.REJECTED);
            responseByRenterRepository.save(respons);
        }
        responseByRenterRepository.save(response);
        offerByOwnerRepository.save(offer);
        return "Zaakceptowano ofertę";
    }

//    public List<ResponseByRenter> getResponsesByOfferId(long id) {
//        return null;
//    }
}
