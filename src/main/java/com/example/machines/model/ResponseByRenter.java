package com.example.machines.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    @Enumerated(value = EnumType.STRING)
    private ResponseByRenterStatus status;


    @ManyToOne
    @JoinColumn(name = "renter_id")
    //@JsonBackReference
    private Renter renter;


    @ManyToOne
    //@JsonBackReference
    private OfferByOwner offer;
}
