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


public class CustomerBookingsDTO {
    private List<BookingDTO> bookings;
}
