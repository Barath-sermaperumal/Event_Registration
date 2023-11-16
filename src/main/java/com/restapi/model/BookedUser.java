package com.restapi.model;

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
public class BookedUser {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    private String Gender;
//
//    @OneToMany(mappedBy = "bookedUserList")
//    private List<Order> order;
}
