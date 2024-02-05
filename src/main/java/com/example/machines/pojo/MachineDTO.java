package com.example.machines.pojo;

import com.example.machines.model.Machine;
import lombok.Getter;

@Getter
public class MachineDTO {
    private Machine machine;
    private EmailDTO email;
}
