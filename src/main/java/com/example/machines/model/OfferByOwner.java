package com.example.machines.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.ArrayList;
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

    @OneToOne(cascade=CascadeType.ALL)
    private Machine machine;

    @ManyToOne(cascade=CascadeType.ALL)
    private Owner owner;

    @OneToMany
    List<ResponseByRenter> responses;


    public static OfferByOwner createOfferByOwner(Owner owner, Date start, Date end, Machine machineType) {
        return new OfferByOwner.Builder()
                .setStartDate(start)
                .setEndDate(end)
                .setMachine(machineType)
                .setOwner(owner)
                .setResponses()
                .build();
    }

    /////// Builder //////////

    public OfferByOwner(Builder builder) {
        this.startAvailabilityDate = builder.startAvailabilityDate;
        this.endAvailabilityDate = builder.endAvailabilityDate;
        this.machine = builder.machine;
        this.owner = builder.owner;
        this.responses = builder.responsesByRenters;
    }

    public static class Builder {
        private Date startAvailabilityDate;
        private Date endAvailabilityDate;
        private Machine machine;
        private Owner owner;
        private List<ResponseByRenter> responsesByRenters;

        public Builder setStartDate(Date start) {
            this.startAvailabilityDate = start;
            return this;
        }

        public Builder setEndDate(Date end) {
            this.endAvailabilityDate = end;
            return this;
        }

        public Builder setMachine(Machine machine) {
            this.machine = machine;
            return this;
        }

        public Builder setOwner(Owner owner) {
            this.owner = owner;
            return this;
        }

        public Builder setResponses() {
            this.responsesByRenters = new ArrayList<>();
            return this;
        }

        public OfferByOwner build() {
            return new OfferByOwner(this);
        }
    }
}
