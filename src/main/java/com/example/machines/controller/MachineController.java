package com.example.machines.controller;

import com.example.machines.model.Machine;
import com.example.machines.model.Owner;
import com.example.machines.model.User;
import com.example.machines.pojo.MachineDTO;
import com.example.machines.repository.OwnerRepository;
import com.example.machines.repository.UserRepository;
import com.example.machines.service.MachineService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Transactional
@CrossOrigin(originPatterns = "*")

@RequestMapping("/owner")
public class MachineController {

    private final MachineService machineService;
    private final OwnerRepository ownerRepository;
    private final UserRepository userRepository;

    public MachineController(MachineService machineService, OwnerRepository ownerRepository, UserRepository userRepository) {
        this.machineService = machineService;
        this.ownerRepository = ownerRepository;
        this.userRepository = userRepository;
    }
    @Secured("OWNER")
    @PostMapping("/machine/add")
    public String addMachine(@RequestBody MachineDTO machine, HttpServletRequest request){
       // System.out.println(machine);
        machineService.addMachine(machine,request);
        return "Dodano maszynę";
    }
    @Secured("OWNER")
    @PostMapping("/machines")
    public List<Machine> getMachines(@RequestBody String email){
        System.out.println("Email form controller: "+ email);
        return machineService.getAllMachinesByOwner(email);
      //  return machineService.getAllMachinesByOwner(ownerRepository.findOwnerById(1));// Todo by session
    }

//    @Secured("OWNER")
//    @GetMapping("/machines")
//    public List<Machine> getMachines(){
//      //  System.out.println("Email form controller: "+ email);
//        System.out.println("owner/machines");
//        return machineService.getAllMachinesByOwner("grzegorz.jarus@gmail.com");
//        //  return machineService.getAllMachinesByOwner(ownerRepository.findOwnerById(1));// Todo by session
//    }
}
