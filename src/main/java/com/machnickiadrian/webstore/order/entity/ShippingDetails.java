package com.machnickiadrian.webstore.order.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity representing an order's shipping details.
 *
 * @author adrian.machnicki
 */
@Getter
@Setter
@Entity
@Table(name = "shipping_details")
public class ShippingDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String address;
    private String city;
}