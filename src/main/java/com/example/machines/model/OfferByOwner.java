package com.example.machines.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
public class OfferByOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date startAvailabilityDate;
    private Date endAvailabilityDate;

    private BigDecimal pricePerDay;
    private BigDecimal deliveryPrice;

    private boolean offerWithOperator;

    @OneToOne(cascade=CascadeType.ALL)
    private Machine machine;

    @Enumerated(value = EnumType.STRING)
    private OfferStatus status;

    @ManyToOne(cascade=CascadeType.ALL)
    private Owner owner;

    @OneToMany(cascade=CascadeType.ALL)
    @JsonIgnore
    List<ResponseByRenter> responses;



    public static OfferByOwner createOfferByOwner(Owner owner, Date start, Date end, BigDecimal pricePerDay, BigDecimal deliveryPrice, boolean operator, Machine machineType) {
        return new OfferByOwner.Builder()
                .setStartDate(start)
                .setEndDate(end)
                .setPricePerDay(pricePerDay)
                .setDeliveryPrice(deliveryPrice)
                .setOfferWithOperator(operator)
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
        this.offerWithOperator=builder.offerWithOperator;
        this.deliveryPrice=builder.deliveryPrice;
        this.pricePerDay=builder.pricePerDay;
    }

    public boolean getOfferWithOperator() {
        return this.offerWithOperator;
    }

    public static class Builder {
        private Date startAvailabilityDate;
        private Date endAvailabilityDate;
        private BigDecimal pricePerDay;
        private BigDecimal deliveryPrice;

        private boolean offerWithOperator;
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

        public Builder setPricePerDay(BigDecimal price){
            this.pricePerDay = price;
            return this;
        }

        public Builder setDeliveryPrice(BigDecimal price){
            this.deliveryPrice = price;
            return this;
        }

        public Builder setOfferWithOperator(boolean operator){
            this.offerWithOperator = operator;
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
