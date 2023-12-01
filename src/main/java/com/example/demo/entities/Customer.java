package com.example.demo.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="customers")
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Customer_ID")
    private Long id;

    @Column(name = "Customer_First_Name", nullable = false)
    private String firstName;

    @Column(name = "Customer_Last_Name", nullable = false)
    private String lastName;

    @Column(name = "Address", nullable = false)
    private String address;

    @Column(name = "Postal_Code")
    private String postal_code;
//Nullable=false goes next to each column where required note to self
    @Column(name = "Phone", nullable = false)
    private String phone;

    @Column(name = "Create_Date")
    @CreationTimestamp
    private Date create_date;

    @Column(name = "Last_Update")
    @UpdateTimestamp
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "Division_ID")
    private Division division;

    @OneToMany(mappedBy = "customer")
    private Set<Cart> carts = new HashSet<>();
}