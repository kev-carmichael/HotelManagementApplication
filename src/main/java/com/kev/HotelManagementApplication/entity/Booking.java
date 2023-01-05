package com.kev.HotelManagementApplication.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "booking")
public class Booking {
    @Id
    private int bookingId;
    private LocalDate dateIn;
    private LocalDate dateOut;

    @ManyToOne
    @JoinColumn(name = "customer", nullable = false) //must make sure column name is matched in the table in flyway
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "room", nullable = false) //must make sure column name is matched in the table in flyway
    private Room room;

}
