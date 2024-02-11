package com.example.machines.service;

import com.example.machines.model.OfferByOwner;
import com.example.machines.model.Renter;
import com.example.machines.model.ResponseByRenter;
import com.example.machines.pojo.ResponseToOfferDTO;
import com.example.machines.repository.OfferByOwnerRepository;
import com.example.machines.repository.RenterRepository;
import com.example.machines.repository.ResponseByRenterRepository;
import jakarta.transaction.Transactional;
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

//    public String sendRequestToOffer(ResponseToOfferDTO response){
//        ResponseByRenter responseByRenter = new ResponseByRenter();
//        responseByRenter.setRenter(renterRepository.findRenterByEmail(response.getEmail()));
//        responseByRenter.setStartRentDate(response.getStart());
//        responseByRenter.setEndRentDate(response.getEnd());
//        responseByRenter.setPrice(response.getPrice());
//        OfferByOwner offer = offerByOwnerRepository.findOfferById(response.getOfferId());
//        System.out.println("Add response: " + offer.getStartAvailabilityDate());
//        offer.getResponses().add(responseByRenter);
//        responseByRenter.setOffer(offer);
//        responseByRenterRepository.save(responseByRenter);
//        offerByOwnerRepository.save(offer);
//
//       // responseByRenter.setOffer(offerByOwnerRepository.findOfferById(response.getOfferId()));
//       // offerByOwnerRepository.
//
//        return "Dodano zapytanie";
//    }
    @Transactional
public String sendRequestToOffer(ResponseToOfferDTO response){
    Renter renter = renterRepository.findRenterByEmail(response.getEmail());
    OfferByOwner offer = offerByOwnerRepository.findOfferById(response.getOfferId());

    if (renter == null) {
        // Handle case where renter with the given email is not found
        return "Renter not found";
    }

    if (offer == null) {
        // Handle case where offer with the given ID is not found
        return "Offer not found";
    }

    ResponseByRenter responseByRenter = new ResponseByRenter();
    responseByRenter.setRenter(renter);
    responseByRenter.setStartRentDate(response.getStart());
    responseByRenter.setEndRentDate(response.getEnd());
    responseByRenter.setPrice(response.getPrice());
    responseByRenter.setOffer(offer); // Set the offer association

    try {
        responseByRenterRepository.save(responseByRenter);
        // Add the response to the offer's responses list
        offer.getResponses().add(responseByRenter);
        offerByOwnerRepository.save(offer);
        return "Dodano zapytanie";
    } catch (Exception e) {
        e.printStackTrace();
        return "Failed to send request"; // Handle exception appropriately
    }
}

}
