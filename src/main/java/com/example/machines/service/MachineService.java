package com.example.machines.service;

import com.example.machines.model.*;
import com.example.machines.pojo.MachineDTO;
import com.example.machines.repository.AddressRepository;
import com.example.machines.repository.MachineRepository;
import com.example.machines.repository.OwnerRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService {

    private final MachineRepository machineRepository;
    private final OwnerRepository ownerRepository; // Todo by session
    private final AddressRepository addressRepository;

    @Autowired
    private final HttpSession session;

    public MachineService(MachineRepository machineRepository, OwnerRepository ownerRepository, AddressRepository addressRepository, HttpSession session) {
        this.machineRepository = machineRepository;
        this.ownerRepository = ownerRepository;
        this.addressRepository = addressRepository;
        this.session = session;
    }

    public Machine addMachine(MachineDTO machineDTO, HttpServletRequest request) {

        Machine machine = machineDTO.getMachine();
        String email =   machineDTO.getEmail().getEmail();
        System.out.println("Email:"+email);
        //Owner owner = ownerRepository.findOwnerByEmail(email+".");
        //HttpSession httpSession= request.getSession();
//        String emailFromSession = (String) session.getAttribute("email");
//        System.out.println("Email form session " + emailFromSession);
        Owner owner = ownerRepository.findOwnerByEmail(email);
        machine.setOwner(owner);
        machine.setStatus(MachineStatus.FREE);
        owner.getMachines().add(machine);
        machineRepository.save(machine);
        //ownerRepository.save(owner);
        return machine;
    }

    //    public List<Machine> getAllMachinesByOwner(User user){
//        return machineRepository.findAllMachinesByOwnerId(user.getId());
//
//    }
    public List<Machine> getAllMachinesByOwner(String email) {
        System.out.println("Email form service:" + email);
        String formattedEmail = email.substring(1, email.length() - 1);
        Owner owner = ownerRepository.findOwnerByEmail(formattedEmail);
        System.out.println(owner.getCompanyName());

        //return machineRepository.findAllMachinesByOwnerEmail(email);
        return machineRepository.findAllMachinesByOwner(owner);

    }


}
