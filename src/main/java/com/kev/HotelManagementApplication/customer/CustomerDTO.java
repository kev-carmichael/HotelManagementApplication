package com.kev.HotelManagementApplication.customer;


import com.kev.HotelManagementApplication.booking.BookingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter

public class CustomerDTO {
    private final int customerId;
    private final String name;
    private final LocalDate dob;
    private final String address;
    private int numberOfBookings;
    private List<BookingDTO> bookings;
}
