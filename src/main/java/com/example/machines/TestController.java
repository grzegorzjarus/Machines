package com.example.machines;

import com.example.machines.model.*;
import com.example.machines.repository.MachineRepository;
import com.example.machines.repository.OwnerRepository;
import com.example.machines.service.OwnerService;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Set;


@RestController
@Transactional
@CrossOrigin(originPatterns = "*")
public class TestController {



    private final OwnerService ownerService;
    private final OwnerRepository ownerRepository;
    private final MachineRepository machineRepository;

    private final Validator validator;

    @Autowired
    public TestController(OwnerService ownerService, OwnerRepository ownerRepository, MachineRepository machineRepository, Validator validator) {

        this.ownerService = ownerService;
        this.ownerRepository = ownerRepository;
        this.machineRepository = machineRepository;

        this.validator = validator;
    }

    @GetMapping("/")

    public Owner testMethod() {
//        String str = "level";
//        StringBuilder sb= new StringBuilder(str);
//        if(str.equals(sb.reverse()))
//            sb.append(sb);

       return ownerRepository.findOwnerById(1);
    }

    @GetMapping("/createOffer")
    public OfferByOwner createOffer(){
        Owner owner = new Owner();
        owner.setCompanyName("BudMax");
        owner.setEmail("budmax@gmail.com");
        owner.setSurname("Jaruss");
        owner.setName("Grzegorz");
        owner.setPhoneNumber("796435802");

        Address address = new Address();
        address.setCountry("Polandd");
        address.setRegion("Dolnośląskie");
        address.setCity("Wrocław");
        address.setStreet("Rychtalska");
        address.setBuildingNumber("2A");
        address.setFlatNumber("-");
        address.setPostalCode("50-304");
        owner.setAddress(address);

        String name = "Maszyna 1";

        Machine machine = new Machine();
        machine.setDescription("Opis");
        machine.setType(MachineType.EXCAVATOR);
        machine.setOwner(owner);
        machine.setName(name);
        machineRepository.save(machine);



        Address address2 = new Address();
        address.setCountry("Polandd");
        address.setRegion("Dolnośląskie");
        address.setCity("Wrocław");
        address.setStreet("Rychtalska");
        address.setBuildingNumber("2A");
        address.setFlatNumber("-");
        address.setPostalCode("50-304");

        Owner owner2 = Owner.builder()
                .email("mail")
                .address(address2)
                .surname("litwin")
                .name("Daniel")
                .companyName("Ti mobajl")
                .build();
        ownerRepository.save(owner2);




        return ownerService.createOffer(owner,new Date(), new Date(),name);

    }

    @GetMapping("/validate")
    public Owner validate2(){

        Address address3 = new Address();
        address3.setCountry("Polandd");
        address3.setRegion("Dolnośląskie");
        address3.setCity("Wrocław");
        address3.setStreet("Rychtalska");
        address3.setBuildingNumber("2A");
        address3.setFlatNumber("-");
        address3.setPostalCode("50-304");

        Owner owner2 = Owner.builder()

                .address(address3)
                .surname("litwin")
                .name("Daniel")
                .companyName("Ti mobajl")
                .build();

        Set<ConstraintViolation<Owner>> violations = validator.validate(owner2);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Owner> constraintViolation : violations) {
                //logger.debug(constraintViolation.getPropertyPath() + " "
                System.out.println(constraintViolation.getMessage());
                //  + constraintViolation.getMessage()); }
            }
        } else {
                ownerRepository.save(owner2);
        }

        return owner2;

    }

    @GetMapping
    public String createUser(){
//        User user1 = Owner.builder().email("Owner@mail").name("Owner").phoneNumber("123").build();
//        User user2 = Renter.builder().email("Renter@mail").name("renter").build();

//        ownerRepository.save(user1);

        return "<h1>Welcome</h1>";

    }


}
