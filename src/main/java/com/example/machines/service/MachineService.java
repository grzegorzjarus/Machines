package com.example.machines.service;

import com.example.machines.model.Address;
import com.example.machines.model.Machine;
import com.example.machines.model.Owner;
import com.example.machines.repository.AddressRepository;
import com.example.machines.repository.MachineRepository;
import com.example.machines.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MachineService {

    private final MachineRepository machineRepository;
    private final OwnerRepository ownerRepository; // Todo by session
    private final AddressRepository addressRepository;

    public MachineService(MachineRepository machineRepository, OwnerRepository ownerRepository, AddressRepository addressRepository) {
        this.machineRepository = machineRepository;
        this.ownerRepository = ownerRepository;
        this.addressRepository = addressRepository;
    }

    public Machine addMachine(Machine machine){
//        Address address = new Address();
//        address.setCountry("Polandd");
//        address.setRegion("Dolnośląskie");
//        address.setCity("Wrocław");
//        address.setStreet("Rychtalska");
//        address.setBuildingNumber("2A");
//        address.setFlatNumber("-");
//        address.setPostalCode("50-304");
//        addressRepository.save(address);
//        Owner owner = Owner.builder()
//                .email("mail")
//                .address(address)
//                .surname("litwin")
//                .name("Daniel")
//                .companyName("Ti mobajl")
//                .phoneNumber("796 435 802")
//                .build();
//        ownerRepository.save(owner);
        machine.setOwner(ownerRepository.findOwnerById(1));
        machineRepository.save(machine);
        return machine;
    }

    public List<Machine> getAllMachinesByOwner(Owner owner){
        return machineRepository.findAllByOwner(owner);

    }

}
