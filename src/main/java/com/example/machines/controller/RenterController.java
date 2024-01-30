package com.example.machines.controller;

import com.example.machines.model.OfferByOwner;
import com.example.machines.pojo.ResponseToOfferDTO;
import com.example.machines.service.OfferByOwnerService;
import com.example.machines.service.RenterService;
import jakarta.transaction.Transactional;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(originPatterns = "*")
@Transactional
@RequestMapping("/renter")
public class RenterController {


    private final OfferByOwnerService offerByOwnerService;
    private final RenterService renterService;

    public RenterController(OfferByOwnerService offerByOwnerService, RenterService renterService) {
        this.offerByOwnerService = offerByOwnerService;
        this.renterService = renterService;
    }

    @GetMapping("/offer")
    public List<OfferByOwner> getAllOffer() {
        return offerByOwnerService.getAllOffer();

    }

    @PostMapping("/offer/response")

    public String sendResponseToOffer(@RequestBody ResponseToOfferDTO response){
        renterService.sendRequestToOffer(response);
        return "Dodano zapytanie";
    }
}
