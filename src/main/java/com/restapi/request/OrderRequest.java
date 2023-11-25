package com.restapi.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restapi.model.AppUser;
import com.restapi.model.BookedUser;
import com.restapi.model.Event;
import com.restapi.model.Seat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private Long userId;
    private Long eventId;
    private List<Seat> bookedSeats;
}
