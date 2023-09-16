package com.example.machines.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Owner  {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String name;
    private String surname;
    private String companyName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @NotEmpty(message = "Nie może być puste")
    private String phoneNumber;
    @Column(unique=true)
    private String email;

    @OneToOne(cascade=CascadeType.ALL)
    private Address address;

    @OneToMany(cascade=CascadeType.ALL)
    List<Machine> machines;

    @OneToMany(cascade = CascadeType.ALL)
    List<OfferByOwner> offers;

    @OneToOne
    @JoinColumn(name = "user_id")
    User user;

    public void addMachine(Machine machine){
        machines.add(machine);
    }

    public Machine getMachineByName(String name){
        for (Machine machine : this.getMachines()) {
            if(machine.getName().equals(name))
                return machine;
        }return null;
    }

}
