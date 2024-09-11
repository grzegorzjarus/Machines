package com.example.machines.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Machine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(value = EnumType.STRING)
    private MachineType type;

    private String name;

    private String description;

    @ManyToOne
    //@JsonBackReference
    // @JsonIgnore
    private Owner owner;

    @Enumerated(value = EnumType.STRING)
    private MachineStatus status;

    @OneToOne(mappedBy = "machine")
   // @JsonBackReference

    private OfferByOwner offer;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JsonIgnore
//    List<OfferByOwner> offers;

    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", type='" + type + '\'' +
                // ... other fields
                ", owner=" + getOwnerSummary() +
                '}';
    }

    private String getOwnerSummary() {
        if (owner == null) {
            return "null";
        }
        return "{id=" + owner.getId() + ", name=" + owner.getName() + "}";
    }
}
