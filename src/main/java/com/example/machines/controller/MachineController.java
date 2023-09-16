package com.example.machines.controller;

import com.example.machines.model.Machine;
import com.example.machines.repository.OwnerRepository;
import com.example.machines.service.MachineService;
import jakarta.transaction.Transactional;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@CrossOrigin(originPatterns = "*")

@RequestMapping("/owner")
public class MachineController {

    private final MachineService machineService;
    private final OwnerRepository ownerRepository;

    public MachineController(MachineService machineService, OwnerRepository ownerRepository) {
        this.machineService = machineService;
        this.ownerRepository = ownerRepository;
    }

    @PostMapping("/machine/add")
    public String addMachine(@RequestBody Machine machine){
        System.out.println(machine);
        machineService.addMachine(machine);
        return "Dodano maszynę";
    }
    @GetMapping("/machines")
    public List<Machine> getMachines(){
        System.out.println( machineService.getAllMachinesByOwner(ownerRepository.findOwnerById(1)));
        //machineService.getAllMachinesByOwner(ownerRepository.findOwnerById(1));
        return machineService.getAllMachinesByOwner(ownerRepository.findOwnerById(1));// Todo by session
    }
}
