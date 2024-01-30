package com.example.machines.pojo;

import com.example.machines.model.OfferByOwner;
import lombok.Data;

import java.util.Date;

@Data
public class OfferAndEmailDTO {
    private OfferByOwner offer;
    EmailDTO email;
    Date start;
    Date end;
}
