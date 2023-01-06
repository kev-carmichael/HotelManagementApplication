package com.kev.HotelManagementApplication.booking;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping(path = "/delete/{id}")
    public boolean deleteBooking(@PathVariable(name = "id") int id)
    {
        return bookingService.deleteBooking(id);
    }

}
