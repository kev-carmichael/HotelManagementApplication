package com.kev.HotelManagementApplication.booking;


import com.kev.HotelManagementApplication.customer.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter

public class BookingDTO {
    private final int bookingId;
    //private final LocalDate dateIn;
    //private final LocalDate dateOut;
    private final String customer;

}
