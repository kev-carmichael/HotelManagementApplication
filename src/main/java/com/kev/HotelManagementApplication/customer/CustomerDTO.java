package com.kev.HotelManagementApplication.customer;


import com.kev.HotelManagementApplication.booking.BookingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter

public class CustomerDTO {
    @Min(value = 1, message = "customerId must be greater than zero")
    private final int customerId;

    @NotBlank(message = "Name cannot be blank")
    private final String name;

    //Handled using DobInFutureException
    private final LocalDate dob;

    @NotBlank(message = "Address cannot be blank")
    private final String address;

    @Min(value = 0, message = "numberOfBookings must be equal to or greater than zero")
    private int numberOfBookings;

    private List<BookingDTO> bookings;

    @NotBlank(message = "Token cannot be blank")
    private String token;
}
