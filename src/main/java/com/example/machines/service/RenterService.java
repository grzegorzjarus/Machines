package com.example.machines.service;

import com.example.machines.model.OfferByOwner;
import com.example.machines.model.ResponseByRenter;
import com.example.machines.pojo.ResponseToOfferDTO;
import com.example.machines.repository.OfferByOwnerRepository;
import com.example.machines.repository.RenterRepository;
import com.example.machines.repository.ResponseByRenterRepository;
import org.springframework.stereotype.Service;

@Service
public class RenterService {
    private final OfferByOwnerRepository offerByOwnerRepository;
    private final RenterRepository renterRepository;
    private final ResponseByRenterRepository responseByRenterRepository;

    public RenterService(OfferByOwnerRepository offerByOwnerRepository, RenterRepository renterRepository, ResponseByRenterRepository responseByRenterRepository) {
        this.offerByOwnerRepository = offerByOwnerRepository;
        this.renterRepository = renterRepository;
        this.responseByRenterRepository = responseByRenterRepository;
    }

    public String sendRequestToOffer(ResponseToOfferDTO response){
        ResponseByRenter responseByRenter = new ResponseByRenter();
        responseByRenter.setRenter(renterRepository.findRenterByEmail(response.getEmail()));
        responseByRenter.setStartRentDate(response.getStart());
        responseByRenter.setEndRentDate(response.getEnd());
        responseByRenter.setPrice(response.getPrice());
        OfferByOwner offer = offerByOwnerRepository.findOfferById(response.getOfferId());
        System.out.println("Add response: " + offer.getStartAvailabilityDate());
        offer.getResponses().add(responseByRenter);
        responseByRenter.setOffer(offer);
        responseByRenterRepository.save(responseByRenter);
        offerByOwnerRepository.save(offer);

       // responseByRenter.setOffer(offerByOwnerRepository.findOfferById(response.getOfferId()));
       // offerByOwnerRepository.

        return "Dodano zapytanie";
    }
}
