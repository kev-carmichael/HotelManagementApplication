package com.kev.HotelManagementApplication.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String name;
    private LocalDate dob;

    @ManyToOne
    @JoinColumn(name = "address", nullable = false)
    private Address address;

    @OneToMany(mappedBy = "customer")
    @OrderBy(value = "bookingId")
    private List<Booking> bookings;
    private String token;

    @Override
    public String toString()
    {
        return String.format(
                "%s, %s",
                name,
                address);
    }

    public int getBookingCount()
    {
        return bookings.size();
    }

}
