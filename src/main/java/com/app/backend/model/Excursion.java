package com.app.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "excursions")
public class Excursion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long excursionId;

    private String excursionTitle;
    private double excursionPrice;
    private String imageURL;
    private Date createDate;
    private Date lastUpdate;


    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "excursion_cartItems",
            joinColumns = @JoinColumn(name = "excursion_id"),
            inverseJoinColumns = @JoinColumn(name = "cart_item_id"))
    private List<CartItem> cartItems;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "vacation_id")
    private Vacation vacation;

}
