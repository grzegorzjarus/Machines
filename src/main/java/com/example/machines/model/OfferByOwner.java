package com.example.machines.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OfferByOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date startAvailabilityDate;
    private Date endAvailabilityDate;

    @OneToOne
    private Machine machine;

    @OneToOne
    private Owner owner;

    @OneToMany
    List<ResponseByRenter> responses;
}
