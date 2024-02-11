package com.example.machines.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import lombok.ToString;

import java.math.BigDecimal;
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

    private BigDecimal price;

    @OneToOne
    private Renter renter;

//    @OneToOne
//    private Machine machine;

    @ManyToOne(cascade=CascadeType.ALL)
    private OfferByOwner offer;
}
