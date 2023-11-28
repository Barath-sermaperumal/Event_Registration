package com.restapi.request;

import com.restapi.model.Seat;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private Long userId;
    private Long eventId;
    private long count;
    private List<Seat> bookedSeats;
}
