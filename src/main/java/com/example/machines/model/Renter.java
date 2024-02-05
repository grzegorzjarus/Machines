package com.example.machines.model;

import lombok.*;

import jakarta.persistence.*;
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
//    private String email;

    @OneToOne
    private Address address;

//
//   @OneToOne
//   @JoinColumn(name = "user_id")
//   private User user;

}
