package com.kev.HotelManagementApplication.booking;


import com.kev.HotelManagementApplication.customer.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@AllArgsConstructor
//@RequiredArgsConstructor
@Getter
@Setter

public class BookingDTO {
    @Min(value = 1, message = "bookingId must be greater than zero")
    private final int bookingId;

    //Handled using DateInIsSameAsOrAfterDateOutException
    private final LocalDate dateIn;
    private final LocalDate dateOut;
    private final String customer;
    private final String Room;

}
