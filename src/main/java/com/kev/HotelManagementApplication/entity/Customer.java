package com.kev.HotelManagementApplication.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    private int customerId;
    private String name;

    @ManyToOne // OR NONE AS PER ERD DIAGRAM?
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    @Override
    public String toString()
    {
        return String.format(
                "%s, %s",
                name,
                address);
    }

}
