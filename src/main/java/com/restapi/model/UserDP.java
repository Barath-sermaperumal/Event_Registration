package com.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDP {

    @Id
    @GeneratedValue
    private long id;

    private String dp;

    @OneToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private AppUser user;
}
