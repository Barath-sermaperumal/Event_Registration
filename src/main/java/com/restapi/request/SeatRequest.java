package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatRequest {
    private long userid;
    private long eventid;
    private String seatnumber;
    private boolean isbooked;
    private long orderid;
}
