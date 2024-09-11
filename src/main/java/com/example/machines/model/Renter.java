package com.example.machines.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import jakarta.persistence.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Renter  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String email;

    private String name;
    private String surname;
    private String companyName;
    private String phoneNumber;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "renter", cascade = CascadeType.ALL)
    //@JsonManagedReference
    private List<ResponseByRenter> responses;

//
//   @OneToOne
//   @JoinColumn(name = "user_id")
//   private User user;

}
