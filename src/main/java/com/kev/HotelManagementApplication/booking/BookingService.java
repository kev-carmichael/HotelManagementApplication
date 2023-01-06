package com.kev.HotelManagementApplication.booking;

import com.kev.HotelManagementApplication.entity.Booking;
import com.kev.HotelManagementApplication.factory.DTOFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private DTOFactory dtoFactory;

    //Need to add some logic to methods for dates:
    // e.g. dateIn is at least current date
    // dateOut is after dateIn
    // error if date not in correct format etc.
    public List<BookingDTO> getBookingList(){
        List<BookingDTO> list = new ArrayList<>();
        for (Booking booking : bookingRepository.findAll()){
            BookingDTO bookingDTO = dtoFactory.createDTO(booking);
            list.add(bookingDTO);
        }
        return list;
    }

    public boolean deleteBooking(int id) {
        if (bookingRepository.existsById(id)) {
            try {
                bookingRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                e.printStackTrace(System.err);
                return false;
            }
        }
        return false;
    }

}
