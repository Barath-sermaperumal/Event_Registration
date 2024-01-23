package com.restapi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    private String image;

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

    private int soldTickets=0;

    @JsonIgnore
    @OneToOne()
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    private List<Order> order;

    @OneToMany(mappedBy = "event")
    private List<Seat> seat;

    @Override
    public String toString() {
        return new StringJoiner(", ", Event.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("image='" + image + "'")
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .add("venue='" + venue + "'")
                .add("date=" + date)
                .add("host='" + host + "'")
                .add("price=" + price)
                .add("availableTickets=" + availableTickets)
                .add("soldTickets=" + soldTickets)
                .add("category=" + category)
                .add("order=" + order)
                .add("seat=" + seat)
                .toString();
    }

}
