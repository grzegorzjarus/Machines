package com.example.machines.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String name;
    private String surname;
    private String companyName;
    private String phoneNumber;
    private String email;

    @OneToOne
    private Address address;
    @OneToMany
    List<Machine> machines;
}