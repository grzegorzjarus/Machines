package com.example.machines.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ResponseToOfferDTO {
    long offerId;
    String email;
    Date start;
    Date end;
    BigDecimal price;

}
