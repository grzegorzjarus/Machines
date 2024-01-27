package com.example.machines.controller;

import com.example.machines.model.OfferByOwner;
import com.example.machines.pojo.OfferAndEmailDTO;
import com.example.machines.repository.OwnerRepository;
import com.example.machines.service.MachineService;
import com.example.machines.service.OfferByOwnerService;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
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

    public OfferByOwnerController(MachineService machineService, OwnerRepository ownerRepository, OfferByOwnerService offerByOwnerService) {
        this.machineService = machineService;

        this.offerByOwnerService = offerByOwnerService;
    }

    @PostMapping(value = "/create/{machineId}", consumes = "application/json")
    @Secured("OWNER")
    public String createOffer(@RequestBody OfferAndEmailDTO offerAndEmail, @PathVariable long machineId) {
       String email= offerAndEmail.getEmail().getEmail();
        System.out.println("Email from controller" + email);
        offerByOwnerService.createNewOffer(offerAndEmail.getOffer(),email, machineId);
        System.out.println("machineid = " + machineId);
        LoggerFactory.getLogger(getClass());
        // offerByOwnerService.createNewOffer(offer, Long.parseLong(machineId.substring(1,10)));
        return "Dodano nową ofertę";
    }

    @DeleteMapping(value = "/delete/{machineId}", consumes = "application/json")
    @Secured("OWNER")
    public String deleteOffer(@PathVariable long machineId) {
        offerByOwnerService.deleteOffer(machineId);
        return "Usunięto ofertę";
    }

    @GetMapping("/renter/offer")
    public List<OfferByOwner> getAllOffer() {
        return offerByOwnerService.getAllOffer();

    }

}
