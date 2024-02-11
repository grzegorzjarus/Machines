package com.example.machines.controller;

import com.example.machines.model.OfferByOwner;
import com.example.machines.model.ResponseByRenter;
import com.example.machines.pojo.OfferAndEmailDTO;
import com.example.machines.repository.OwnerRepository;
import com.example.machines.repository.ResponseByRenterRepository;
import com.example.machines.service.MachineService;
import com.example.machines.service.OfferByOwnerService;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
//@CrossOrigin(originPatterns = "*")
@CrossOrigin
@RequestMapping("/owner/offer")
public class OfferByOwnerController {

    private final MachineService machineService;
    private final OfferByOwnerService offerByOwnerService;
    private final ResponseByRenterRepository responseByRenterRepository;

    public OfferByOwnerController(MachineService machineService, OwnerRepository ownerRepository, OfferByOwnerService offerByOwnerService, ResponseByRenterRepository responseByRenterRepository) {
        this.machineService = machineService;

        this.offerByOwnerService = offerByOwnerService;
        this.responseByRenterRepository = responseByRenterRepository;
    }

    @PostMapping(value = "/create/{machineId}", consumes = "application/json")
    @Secured("OWNER")
    public String createOffer(@RequestBody OfferAndEmailDTO offerAndEmail, @PathVariable long machineId) {
       String email= offerAndEmail.getEmail().getEmail();
        System.out.println("Email from controller" + email);
        offerByOwnerService.createNewOffer(offerAndEmail.getOffer(),email, machineId);
        System.out.println("machineid = " + machineId);
       // LoggerFactory.getLogger(getClass());
        // offerByOwnerService.createNewOffer(offer, Long.parseLong(machineId.substring(1,10)));
        return "Dodano nową ofertę";
    }

    @DeleteMapping(value = "/delete/{machineId}", consumes = "application/json")
    @Secured("OWNER")
    public String deleteOffer(@PathVariable long machineId) {
        offerByOwnerService.deleteOffer(machineId);
        return "Usunięto ofertę";
    }
//
//    @GetMapping("/renter/offer")
//    public List<OfferByOwner> getAllOffer() {
//        return offerByOwnerService.getAllOffer();
//
//    }
    @GetMapping(value = "/{offerId}/responses", produces = "application/json")
    public List<ResponseByRenter> getResponses(@PathVariable long offerId){
       return offerByOwnerService.getOfferResponses(offerId);
    }

    @PostMapping(value="/active", produces = "application/json")
    public List<OfferByOwner> getAllOnAuctionOfferByOwner(@RequestBody String email){

        return offerByOwnerService.getAllOnAuctionOfferByOwner(email);
    }

    @GetMapping("/active/{offerId}")
    public List<ResponseByRenter> getResponsesByOfferId(@PathVariable long id, @PathVariable String offerId){
        return  responseByRenterRepository.findAllByOfferId(Long.parseLong(offerId));
    }



    @GetMapping("/responses/get")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Test zdany");
    }

}
