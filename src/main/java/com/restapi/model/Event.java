package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 250)
    private String description;

    @Column(nullable = false, length = 250)
    private String venue;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, length = 100)
    private String host;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int availableTickets;

    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<Order> order;
}
