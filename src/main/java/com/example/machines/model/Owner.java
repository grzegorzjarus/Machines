package com.example.machines.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Owner  {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String surname;
    private String companyName;
//
//    @Enumerated(EnumType.STRING)
//    private Role role;

    @NotEmpty(message = "Nie może być puste")
    private String phoneNumber;
    @Column(unique=true)
    private String email;

    @OneToOne(cascade=CascadeType.ALL)
    private Address address;

    @OneToMany(cascade=CascadeType.ALL)
    @JsonIgnore // to avoid infinite loop between entities
    List<Machine> machines;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    List<OfferByOwner> offers;


//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    public void addMachine(Machine machine){
        machines.add(machine);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                // ... other fields
                ", machines=" + getMachineListSummary() +
                '}';
    }

    private String getMachineListSummary() {
        if (machines == null) {
            return "null";
        }
        StringBuilder summary = new StringBuilder("[");
        for (Machine machine : machines) {
            summary.append("{id=").append(machine.getId()).append(", type=").append(machine.getType()).append("}, ");
        }
        if (machines.size() > 0) {
            summary.setLength(summary.length() - 2); // Remove the trailing comma and space
        }
        summary.append("]");
        return summary.toString();
    }

    public Machine getMachineByName(String name){
        for (Machine machine : this.getMachines()) {
            if(machine.getName().equals(name))
                return machine;
        }return null;
    }

}
