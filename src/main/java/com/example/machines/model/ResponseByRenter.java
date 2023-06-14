package com.example.machines.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ResponseByRenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date startRentDate;
    private Date endRentDate;

    @OneToOne
    private Renter renter;

    @OneToOne
    private Machine machine;

    @ManyToOne
    private OfferByOwner offer;
}
