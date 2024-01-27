package com.example.machines.pojo;

import com.example.machines.model.OfferByOwner;
import lombok.Data;

@Data
public class OfferAndEmailDTO {
    private OfferByOwner offer;
    EmailDTO email;
}
