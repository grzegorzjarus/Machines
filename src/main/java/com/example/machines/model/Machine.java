package com.example.machines.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import lombok.ToString;

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

    @ManyToOne(cascade = CascadeType.ALL)
    private Owner owner;

    @Enumerated(value = EnumType.STRING)
    private MachineStatus status;

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
        return "{id=" + owner.getId() + ", name=" + owner.getName() + "}";}
    }
