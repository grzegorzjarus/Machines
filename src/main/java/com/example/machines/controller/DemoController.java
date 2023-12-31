package com.example.machines.controller;

import com.example.machines.model.Machine;
import com.example.machines.repository.OwnerRepository;
import com.example.machines.service.MachineService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo")
@CrossOrigin(originPatterns = "*")
//@CrossOrigin()
public class DemoController {




    private final MachineService machineService;
    private final OwnerRepository ownerRepository;

    public DemoController(MachineService machineService, OwnerRepository ownerRepository) {
        this.machineService = machineService;
        this.ownerRepository = ownerRepository;
    }

    @Secured("OWNER")
    @PostMapping("/machine/add")
    public String addMachine(@RequestBody Machine machine){
        System.out.println(machine);
        machineService.addMachine(machine);
        return "Dodano maszynę";
    }
    @Secured("OWNER")
    @GetMapping("/machines")
    public List<Machine> getMachines(){
        System.out.println( machineService.getAllMachinesByOwner(ownerRepository.findOwnerById(1)));
        //machineService.getAllMachinesByOwner(ownerRepository.findOwnerById(1));
        return machineService.getAllMachinesByOwner(ownerRepository.findOwnerById(1));// Todo by session
    }



    @Secured("OWNER")
    @GetMapping("/demo-owner")
    public ResponseEntity<String> sayHello(){
       return ResponseEntity.ok("Hello owner from secured endpoint");
    }

    @Secured("USER")
    @GetMapping("/demo-user")
    public ResponseEntity<String> sayHelloToUser(){
        return ResponseEntity.ok("Hello user from secured endpoint");
    }
}
