package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    @Id
    @GeneratedValue
    private long id;

    private String seatNumber;

    private boolean isSeatBooked;

    @ManyToOne
    @JoinColumn(name = "orderId",referencedColumnName = "id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "eventId",referencedColumnName = "id")
    private Event event;
}
