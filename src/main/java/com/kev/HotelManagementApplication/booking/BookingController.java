package com.kev.HotelManagementApplication.booking;


import com.kev.HotelManagementApplication.roomType.RoomTypeDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @PostMapping(path = "/update/{id}/{datein}/{dateout}")
    public BookingDTO updateBooking(@PathVariable(name = "id") int id,
                                    @PathVariable(name = "datein")String dateIn,
                                    @PathVariable(name = "dateout")String dateOut) {
        return bookingService.updateBooking(id, dateIn, dateOut);
    }


}
