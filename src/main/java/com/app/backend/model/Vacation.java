package com.app.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "vacations")
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vacationId;
    private String vacationTitle;
    private String description;
    private double travelFarePrice;
    private Date createDate;
    private Date lastUpdate;

    @JsonIgnore
    @OneToMany(mappedBy = "vacation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "vacation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Excursion> excursions = new ArrayList<>();

}
