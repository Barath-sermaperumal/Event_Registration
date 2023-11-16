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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private AppUser users;

    @ManyToOne()
    @JoinColumn(name = "eventId",referencedColumnName = "id")
    private Event event;

    private int count;
//
//    @ManyToOne()
//    @JoinColumn(name = "bookeduser_id", referencedColumnName = "id")
//    private BookedUser bookedUserList;

}

