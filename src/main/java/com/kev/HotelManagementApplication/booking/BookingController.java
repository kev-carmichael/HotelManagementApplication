package com.kev.HotelManagementApplication.booking;


import com.kev.HotelManagementApplication.factory.DTOFactory;
import com.kev.HotelManagementApplication.roomType.RoomTypeDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/booking")
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    private final DTOFactory dtoFactory;

    @GetMapping(path="/all")
    public List<BookingDTO> getBookingList(){
        return bookingService.getBookingList();
    }

    @PostMapping(path = "/add/{customerid}/{datein}/{dateout}/{roomid}")
    public BookingDTO addBooking(@PathVariable("customerid") int customerid,
                                 @PathVariable("datein") String dateIn,
                                 @PathVariable("dateout") String dateOut,
                                 @PathVariable("roomid") int roomid)
    {
        return dtoFactory.createDTO(bookingService.createBooking
                (customerid, dateIn, dateOut, roomid));
    }


    @DeleteMapping(path = "/delete/{customerid}/{id}")
    public boolean deleteBooking(@PathVariable("customerid")
                                     @Min(value = 1, message = "customerId must be greater than zero") int customerid,
                                    @PathVariable(name = "id")
                                    @Min(value = 1, message = "bookingId must be greater than zero") int id)
    {
        return bookingService.deleteBooking(id);
    }

    @PostMapping(path = "/update/{customerid}/{id}/{datein}/{dateout}")
    public BookingDTO updateBooking(@PathVariable("customerid")
                                        @Min(value = 1, message = "customerId must be greater than zero") int customerid,
                                    @PathVariable(name = "id")
                                    @Min(value = 1, message = "bookingId must be greater than zero") int id,
                                    @PathVariable(name = "datein")String dateIn,
                                    @PathVariable(name = "dateout")String dateOut) {
        return bookingService.updateBooking(id, dateIn, dateOut);
    }


}
