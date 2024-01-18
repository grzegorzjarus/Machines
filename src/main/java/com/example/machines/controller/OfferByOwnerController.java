package com.example.machines.controller;

import com.example.machines.model.OfferByOwner;
import com.example.machines.repository.OwnerRepository;
import com.example.machines.service.MachineService;
import com.example.machines.service.OfferByOwnerService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
//@CrossOrigin(originPatterns = "*")
@CrossOrigin
@RequestMapping("/owner/offer")
public class OfferByOwnerController {

    private final MachineService machineService;
    private final OfferByOwnerService offerByOwnerService;

    public OfferByOwnerController(MachineService machineService, OwnerRepository ownerRepository, OfferByOwnerService offerByOwnerService) {
        this.machineService = machineService;

        this.offerByOwnerService = offerByOwnerService;
    }

    @PostMapping(value = "/create/{machineId}", consumes = "application/json")
    @Secured("OWNER")
    public String createOffer(@RequestBody OfferByOwner offer, @PathVariable long machineId) {
        offerByOwnerService.createNewOffer(offer, machineId);
        System.out.println("machineid = " +machineId);
       // offerByOwnerService.createNewOffer(offer, Long.parseLong(machineId.substring(1,10)));
        return "Dodano nową ofertę";
    }

    @DeleteMapping(value = "/delete/{machineId}", consumes = "application/json")
    @Secured("OWNER")
    public String deleteOffer(@PathVariable long machineId){
        offerByOwnerService.deleteOffer(machineId);
        return "Usunięto ofertę";
    }


}
