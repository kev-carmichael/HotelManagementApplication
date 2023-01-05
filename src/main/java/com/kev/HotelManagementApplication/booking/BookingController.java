package com.kev.HotelManagementApplication.booking;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/booking")
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping(path="/all")
    public List<BookingDTO> getBookingList(){
        return bookingService.getBookingList();
    }
}
