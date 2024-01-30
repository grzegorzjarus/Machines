package com.example.machines.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class ResponseToOfferDTO {
    long offerId;
    String email;
    Date start;
    Date end;

}
